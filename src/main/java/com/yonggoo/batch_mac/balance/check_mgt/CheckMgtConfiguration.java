package com.yonggoo.batch_mac.balance.check_mgt;


import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtPerson;
import com.yonggoo.batch_mac.balance.check_mgt.processor.ChekMgtItemProcessor;
import com.yonggoo.batch_mac.balance.check_mgt.service.CheckMgtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//mapper mybatis 사용법
//https://veneas.tistory.com/entry/Spring-Boot-MyBatis-%EC%82%AC%EC%9A%A9%EB%B2%95-Mapper-MapperScan

//https://data-make.tistory.com/748

//https://renuevo.github.io/spring/batch/spring-batch-chapter-3/
@Configuration
@Slf4j
public class CheckMgtConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CheckMgtItemReader checkMgtItemReader;
    private final CheckMgtItemWriter checkMgtItemWriter;
    private final CheckMgtService checkMgtService;

    private static final int chunkSize = 30;

    public CheckMgtConfiguration(JobBuilderFactory jobBuilderFactory
            , StepBuilderFactory stepBuilderFactory
            , CheckMgtItemReader checkMgtItemReader
            , CheckMgtItemWriter checkMgtItemWriter
            , CheckMgtService checkMgtService)
    {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.checkMgtItemReader = checkMgtItemReader;
        this.checkMgtItemWriter = checkMgtItemWriter;
        this.checkMgtService = checkMgtService;
    }



    //일단 tasklet으로 처리한 건
    //https://sic-dev.tistory.com/96


    //스프링배치
    //https://mybatis.org/spring/ko/batch.html

//    @Bean
//    public ChekMgtItemProcessor processor() {
//        return new ChekMgtItemProcessor();
//    }

//    @Bean
//    public ChekMgtItemListProcessor processor2() {
//        return new ChekMgtItemListProcessor();
//    }





    @Bean
    @Qualifier("checkMgtJob")
    public Job balanceCheckJob() throws Exception {
        System.out.println("balanceCheckJob start");
        return jobBuilderFactory.get("checkMgtJob")
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(TaskStep())
                .build();
    }



    @Bean
    public Step TaskStep(){
        return stepBuilderFactory.get("taskletStep")
                .tasklet(new BusinessTasklet(checkMgtService))
                .build();
    }





/*
    @Bean
    @Qualifier("checkMgtChunkJob")
    public Job balanceCheckChunkJob() throws Exception {
        System.out.println("balanceCheckJob start");
        return jobBuilderFactory.get("checkMgtChunkJob")
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                //.listener(listener)
                .start(step1())
                .build();
    }


    @Bean
    public Step step1() throws  Exception  {

        //http://localhost:8081/job/balance/checkmgt/collect/countall

        System.out.println("CheckMgtConfiguration step 1 start");
        System.out.println(checkMgtService.CheckCount());
        //디비 호출하는건 찾아냄!!!
        System.out.println("CheckMgtConfiguration step 1 start ing");


        return stepBuilderFactory.get("step1_actor")
                .<CheckMgtActor, CheckMgtActor> chunk(chunkSize)
                .reader(checkMgtItemReader.tradeDbCheckMgtActorRead())
                //.processor((processor())
                .writer(checkMgtItemWriter.adminDbCheckMgtActorWriter())
                .build();
    }
*/






//    @Bean
//    public Step step2() throws  Exception  {
//
//        System.out.println("CheckMgtConfiguration step 2 start");
//
//
//
//        return stepBuilderFactory.get("step2")
//                .<CheckMgtPerson, CheckMgtPerson> chunk(chunkSize)
//                .reader(checkMgtItemReader.tradeDbRead())
//                .processor(processor())
//                .writer(checkMgtItemWriter.writer())
//                .build();
//    }

//    @Bean
//    public Step step3() throws  Exception  {
////        https://spring.io/projects/spring-batch
////        https://hevodata.com/learn/spring-batch-jobs/
////        https://youtu.be/_nkJkWVH-mo
//
//        //test()
//        /* 1. */
//        System.out.println("CheckMgtConfiguration step 3 start");
//
//
//        //actor 아이디...나눠서 처리하는 방법은???
//
//
//        //페이징 처리 해서...조인 처리는??
//        //데이터 읽어 들여서!!
//        //System.out.println(checkMgtService.CheckCount());
//
//
//
//        // 체크로직!!
//        // checkMgtService.test();
//
//        // 통과하면!!
//
//        // step 1. 실행!!
//
//
//        //여러 개의 스텝을 만들고 스텝들이 어떻게 JobExecutionContext를 사용하여 데이터를 공유하는지 알아보자.
//
//        // step 2 실행
//
////        return stepBuilderFactory.get("tutorialStep")
////                .tasklet(new TutorialTasklet()) // Tasklet 설정
////                .build();
//
//        stepBuilderFactory.get("step3-1")
//                .<CheckMgtPerson, CheckMgtPerson> chunk(chunkSize)
//                .reader(checkMgtItemReader.tradeDbRead())
//                .processor(processor())
//                .writer(checkMgtItemWriter.writer())
//                .build();
//
//
//        //진행하다 실패하면??
//
//        /* 2. */
//
//        System.out.println("CheckMgtConfiguration step 3 start");
//
//        /* 3. */
//
//        return stepBuilderFactory.get("step3-2")
//                .<CheckMgtPerson, CheckMgtPerson> chunk(chunkSize)
//                .reader(checkMgtItemReader.tradeDbRead())
//                .processor(processor())
//                .writer(checkMgtItemWriter.writer())
//                .build();
//
//
//    }

//    @Bean
//    public Step step4() throws  Exception  {
//
//        System.out.println("CheckMgtConfiguration step 4 start");
//
//        return stepBuilderFactory.get("step4")
//                .<CheckMgtPerson, CheckMgtPerson> chunk(chunkSize)
//                .reader(checkMgtItemReader.tradeDbRead())
//                .processor(processor())
//                .writer(checkMgtItemWriter.writer())
//                .build();
//    }



//    @Slf4j
//    public class TutorialTasklet implements Tasklet {
//
//        @Override
//        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//            log.debug("executed tasklet !!");
//            return RepeatStatus.FINISHED;
//        }
//    }





}

