package com.yonggoo.batch_mac.balance.check_mgt;

import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilm;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActorVO;
import com.yonggoo.batch_mac.balance.check_mgt.service.CheckMgtService;
import com.yonggoo.batch_mac.common.configuration.db.AdminDBConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class BusinessTasklet implements Tasklet, StepExecutionListener {

    //tasklet 업그레이드!!!!
    //https://www.baeldung.com/spring-batch-tasklet-chunk

    private static final int CHUNK_SIZE = 1000;

    private CheckMgtService checkMgtService;

    public BusinessTasklet(CheckMgtService checkMgtService) {
        this.checkMgtService = checkMgtService;
    }

    @Autowired
    private AdminDBConfiguration adminDBConfiguration;

    @Autowired
    private PlatformTransactionManager productTransactionManager;

    @Autowired
    private DefaultTransactionDefinition definition;


    @Override
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        log.info("Before Step Start!");

    }

    @Override
    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {

        log.info(stepExecution.getStepName());
        log.info(stepExecution.getSummary());
        log.info("After Step finished!");
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        int maxPage = 0;
        int limit = 0;
        int offset = 0;
        int filmActorAllCnt = 0;

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(transFormat.format(new Date()));

        LocalDate yesterday = LocalDate.parse("2006-01-01");
        today = today.plusDays(3);

        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("today", today);
        map.put("yesterday", yesterday);

        filmActorAllCnt = checkMgtService.selectCheckMgtFilmActorAll(map);

        if(filmActorAllCnt <= 0)
        {
            return RepeatStatus.FINISHED;
        }




        maxPage = (filmActorAllCnt % CHUNK_SIZE) == 0 ? (filmActorAllCnt / CHUNK_SIZE) : (filmActorAllCnt/ CHUNK_SIZE) + 1;


        for(int i=0; i < maxPage; i++)
        {

            offset = (i == 0) ? 0 : i * CHUNK_SIZE;
            limit = CHUNK_SIZE;

            map =  new HashMap<String,Object>() ;
            map.put("today", today);
            map.put("yesterday", yesterday);
            map.put("limit", limit);
            map.put("offset", offset);

            List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);

            if(lcfa2.size()<=0){
                return RepeatStatus.FINISHED;
            }

            List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());

            if(actorList.size()<=0){
                return RepeatStatus.FINISHED;
            }

            List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);

            List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());

            if(filmList.size()<=0){
                return RepeatStatus.FINISHED;
            }

            List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(filmList);

            if(lcf2.size()<=0){
                return RepeatStatus.FINISHED;
            }


            List<CheckMgtFilmActorVO> lcfaVo = this.mergeClass(lcfa2, lcma2, lcf2);

            if(lcfaVo.size() <= 0)
            {
                return RepeatStatus.FINISHED;
            }


            //checkMgtService.insertFilmActorCopied(lcfaVo);
        }

        return RepeatStatus.FINISHED;
    }

    public List<CheckMgtFilmActorVO> mergeClass(List<CheckMgtFilmActor> lcfa2, List<CheckMgtActor> lcma2,  List<CheckMgtFilm> lcf2 )
    {
        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();

        for(CheckMgtFilmActor cfa : lcfa2)
        {
            for(CheckMgtActor cma : lcma2)
            {
                if(cfa.getActorId() == cma.getActorId())
                {
                    for(CheckMgtFilm cf : lcf2)
                    {
                        if(cf.getFilmId() == cfa.getFilmId())
                        {
                            CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(cma.getActorId()
                                    , cma.getFirstName()
                                    , cma.getLastName()
                                    , cf.getFilmId()
                                    , cf.getTitle()
                                    , cf.getDescription()
                                    , cf.getRelease_year()
                                    , cma.getLastUpdate()
                            );

                            lcfaVo.add(cmf);
                        }
                    }
                }
            }
        }

        return lcfaVo;
    }







/*

//https://devocean.sk.com/blog/techBoardDetail.do?ID=164085


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        int maxPage = 0;
        int limit = 0;
        int offset = 0;
        int filmActorAllCnt = 0;

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(transFormat.format(new Date()));
        //LocalDate yesterday = today.plusDays(-3);

        LocalDate yesterday = LocalDate.parse("2006-01-01");
        today = today.plusDays(3);

        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("today", today);
        map.put("yesterday", yesterday);

        filmActorAllCnt = checkMgtService.selectCheckMgtFilmActorAll(map);

        if(filmActorAllCnt <= 0)
        {
            return RepeatStatus.FINISHED;
        }

        maxPage = (filmActorAllCnt % CHUNK_SIZE) == 0 ? (filmActorAllCnt / CHUNK_SIZE) : (filmActorAllCnt/ CHUNK_SIZE) + 1;





        for(int i=0; i < maxPage; i++)
        {
            offset = (i == 0) ? 0 : i * CHUNK_SIZE;
            limit = CHUNK_SIZE;

            map =  new HashMap<String,Object>() ;
            map.put("today", today);
            map.put("yesterday", yesterday);
            map.put("limit", limit);
            map.put("offset", offset);

            List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);

            if(lcfa2.size()<=0){
                return RepeatStatus.FINISHED;
            }

            List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());

            if(actorList.size()<=0){
                return RepeatStatus.FINISHED;
            }

            List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);


//            System.out.println(Integer.toString(i) + "------>CheckMgtActor start");
//            for(CheckMgtActor vo: lcma2)
//            {
//                System.out.println(vo.getActorId());
//            }
//            System.out.println(Integer.toString(i) + "------>CheckMgtActor end");


            List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());

            if(filmList.size()<=0){
                return RepeatStatus.FINISHED;
            }

            List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(filmList);

            if(lcf2.size()<=0){
                return RepeatStatus.FINISHED;
            }



            List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();



            for(CheckMgtFilmActor cfa : lcfa2)
            {
                for(CheckMgtActor cma : lcma2)
                {
                    if(cfa.getActorId() == cma.getActorId())
                    {
                        for(CheckMgtFilm cf : lcf2)
                        {
                            if(cf.getFilmId() == cfa.getFilmId())
                            {
                                CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(cma.getActorId()
                                        , cma.getFirstName()
                                        , cma.getLastName()
                                        , cf.getFilmId()
                                        , cf.getTitle()
                                        , cf.getDescription()
                                        , cf.getRelease_year()
                                        , cma.getLastUpdate()
                                );

                                lcfaVo.add(cmf);
                            }
                        }
                    }
                }
            }


//            System.out.println(Integer.toString(i) + "------> start");
//            for(CheckMgtFilmActorVO vo: lcfaVo)
//            {
//                System.out.println(vo.getActorId());
//                System.out.println(vo.getFilmId());
//            }
//            System.out.println(Integer.toString(i) + "------> end");


            checkMgtService.insertFilmActorCopied(lcfaVo);
        }

        return RepeatStatus.FINISHED;
    }


 */



    /* 샘플22

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        int maxPage = 0;
        int limit = 10;
        int offset = 0;
        int filmActorAllCnt = 0;

        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(transFormat.format(new Date()));
        LocalDate yesterday = today.plusDays(-3);
        today = today.plusDays(3);

        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("today", today);
        map.put("yesterday", yesterday);

        filmActorAllCnt = checkMgtService.selectCheckMgtFilmActorAll(map);

        if(filmActorAllCnt <= 0)
        {
            return RepeatStatus.FINISHED;
        }

        maxPage = (filmActorAllCnt % CHUNK_SIZE) == 0 ? (filmActorAllCnt / CHUNK_SIZE) : (filmActorAllCnt/ CHUNK_SIZE) + 1;

        System.out.println("aaa maxPage=" + Integer.toString(maxPage) );


        for(int i=0; i < maxPage; i++)
        {
            offset = (i == 0) ? 0 : i * CHUNK_SIZE;
            limit = CHUNK_SIZE;

            map =  new HashMap<String,Object>() ;
            map.put("today", today);
            map.put("yesterday", yesterday);
            map.put("limit", limit);
            map.put("offset", offset);

            lcfaVo = new ArrayList<CheckMgtFilmActorVO>();

            List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);

            if(lcfa2.size()<=0){
                return RepeatStatus.FINISHED;
            }

            List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());

            if(actorList.size()<=0){
                return RepeatStatus.FINISHED;
            }


            List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);

            List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());

            if(filmList.size()<=0){
                return RepeatStatus.FINISHED;
            }

            List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(filmList);

            for(CheckMgtFilmActor cfa : lcfa2)
            {
                for(CheckMgtActor cma : lcma2)
                {
                    if(cfa.getActorId() == cma.getActorId())
                    {
                        for(CheckMgtFilm cf : lcf2)
                        {
                            if(cf.getFilmId() == cfa.getFilmId())
                            {
                                CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(cma.getActorId()
                                        , cma.getFirstName()
                                        , cma.getLastName()
                                        , cf.getFilmId()
                                        , cf.getTitle()
                                        , cf.getDescription()
                                        , cf.getRelease_year()
                                        , cma.getLastUpdate()
                                );

                                lcfaVo.add(cmf);
                            }
                        }
                    }
                }
            }

            checkMgtService.insertFilmActorCopied(lcfaVo);
        }

        return RepeatStatus.FINISHED;
    }

    */









/* 샘플
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        int max = 10;

//        productTransactionManager = adminDBConfiguration.productTransactionManager(this.adminDBConfiguration.db2DataSource());
//        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//        TransactionStatus status = productTransactionManager.getTransaction(definition);


        int limit = 10;
        int offset = 0;


        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(transFormat.format(new Date()));
        LocalDate yesterday = today.plusDays(-3);
        today = today.plusDays(3);

        System.out.println("LocalDate after " + " adding days: " + yesterday);
        System.out.println("LocalDate after " + " adding days: " + today);


        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("today", today);
        map.put("yesterday", yesterday);
        map.put("limit", limit);
        map.put("offset", offset);



        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);

        System.out.println(lcfa2.size());

        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();

        if(lcfa2.size()<=0){
            return RepeatStatus.FINISHED;
        }

        List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());

        if(actorList.size()<=0){
            return RepeatStatus.FINISHED;
        }

        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);

        List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());

        if(filmList.size()<=0){
            return RepeatStatus.FINISHED;
        }

        List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(filmList);






        for(CheckMgtFilmActor cfa : lcfa2)
        {
            for(CheckMgtActor cma : lcma2)
            {
                if(cfa.getActorId() == cma.getActorId())
                {
                    for(CheckMgtFilm cf : lcf2)
                    {
                        if(cf.getFilmId() == cfa.getFilmId())
                        {
                            CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(cma.getActorId()
                                    , cma.getFirstName()
                                    , cma.getLastName()
                                    , cf.getFilmId()
                                    , cf.getTitle()
                                    , cf.getDescription()
                                    , cf.getRelease_year()
                                    , cma.getLastUpdate()
                            );

                            lcfaVo.add(cmf);
                        }
                    }
                }
            }
        }

        checkMgtService.insertFilmActorCopied(lcfaVo);


//        //productTransactionManager.rollback(status);
//        productTransactionManager.commit(status);

        return RepeatStatus.FINISHED;
    }
    */

}
