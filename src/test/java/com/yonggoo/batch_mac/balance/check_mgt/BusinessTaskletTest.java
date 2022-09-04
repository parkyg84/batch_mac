package com.yonggoo.batch_mac.balance.check_mgt;

import com.yonggoo.batch_mac.balance.check_mgt.model.*;
import com.yonggoo.batch_mac.balance.check_mgt.service.CheckMgtService;
import com.yonggoo.batch_mac.common.configuration.db.AdminDBConfiguration;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


//스프링 배치 트랜잭션!!
//https://terasoluna-batch.github.io/guideline/5.0.0.RELEASE/en/Ch05_Transaction.html#Ch05_Transaction_Overview_TxType

//트랜잭션!!
//https://terasoluna-batch.github.io/guideline/5.0.0.RELEASE/en/Ch05_Transaction.html

//트랜잭션 부여!!
//https://soonh.tistory.com/41
//@Transactional

//@Getter
//@AllArgsConstructor(staticName = "create")

@SpringBootTest
public class BusinessTaskletTest {

    @Autowired
    public CheckMgtService checkMgtService;

    private static final int CHUNK_SIZE = 10;

    @Autowired
    private AdminDBConfiguration adminDBConfiguration;

    @Autowired
    private PlatformTransactionManager productTransactionManager;


    @Autowired
    private DefaultTransactionDefinition definition;


    @Test
    void testBusinessTaskletTest97() throws Exception
    {

        int limit = 10;
        int offset = 0;

        Date date = new Date();
        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(transFormat.format(date));
        LocalDate yesterday = today.plusDays(-10);
        today = today.plusDays(10);
        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("today", today);
        map.put("yesterday", yesterday);
        map.put("limit", limit);
        map.put("offset", offset);

        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);
        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();
        System.out.println(lcfa2.size());

        List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());
        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);

        List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());
        List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(filmList);



        List<CheckMgtActor> lcfa = checkMgtService.selectCheckMgtActorAll(map);


        Map<Integer, CheckMgtActor> idMap = lcfa.stream()
                .distinct().collect(Collectors.toMap(CheckMgtActor::getActorId, Function.identity()));

        System.out.println(idMap.size());


        //필터 걸어주는것!!
        List<CheckMgtFilmActor> lcfa22 = lcfa2.stream()
                .filter(it -> idMap.containsKey(it.getActorId()))
                .collect(Collectors.toList());

        for(CheckMgtFilmActor vo :lcfa22){

            System.out.println(vo.getActorId());
            System.out.println(vo.getFilmId());

        }

    }


        @Test
    void testBusinessTaskletTest98() throws Exception
    {
        List<User> userList = Lists.newArrayList(User.create(1), User.create(1), User.create(4));
        List<Order> orderList = Lists.newArrayList(Order.create(1, 99), Order.create(2, 98));




//        System.out.println(userList.size());
//        System.out.println(orderList.size());


        //inner
//        Map<Long, User> userIdMap = userList.stream()
//                .collect(Collectors.toMap(User::getUserId, Function.identity()));

//        List<Pair<Order, User,>> innerJoinList = orderList.stream()
//                .filter(it -> userIdMap.containsKey(it.getUserId()))
//                .map(it -> TestPropertyValues.Pair.of(it, userIdMap.get(it.getUserId())))
//                .collect(Collectors.toList());


        //left
        Map<Integer, Order> orderIdMap = orderList.stream().collect(Collectors.toMap(Order::getOrderId, Function.identity()));


        //System.out.println(orderIdMap.get(0).getUserId());
        //System.out.println(orderIdMap.get(0).getUserId());
        System.out.println(orderIdMap.get(1).getOrderId());
        System.out.println(orderIdMap.get(2).getOrderId());


        //System.out.println(orderIdMap.get(1).getUserId());
        //System.out.println(orderIdMap.get(2).getUserId());




        List<User> leftJoinUser = userList.stream()
                .filter(it -> orderIdMap.containsKey(it.getUserId()))
                .collect(Collectors.toList());

        for(User vo : leftJoinUser){
            System.out.println(vo.getUserId());
        }


    }


    @Test
    void testBusinessTaskletTest99() throws Exception
    {
        int max = 10;
//        productTransactionManager = adminDBConfiguration.productTransactionManager(this.adminDBConfiguration.db2DataSource());
//        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);  // (3)
//        TransactionStatus status = productTransactionManager.getTransaction(definition);

        int limit = 10;
        int offset = 0;

        Date date = new Date();
        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(transFormat.format(date));
        LocalDate yesterday = today.plusDays(-10);

        today = today.plusDays(10);

        System.out.println("LocalDate after " + " adding days: " + yesterday);
        System.out.println("LocalDate after " + " adding days: " + today);


        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("today", today);
        map.put("yesterday", yesterday);
        map.put("limit", limit);
        map.put("offset", offset);

        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);


        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();


        System.out.println(lcfa2.size());

        List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());
        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);

        List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());
        List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(filmList);




//        Map<Integer, CheckMgtFilmActor> actorIdMap = lcfa2.stream()
//                .collect(Collectors.toMap(CheckMgtFilmActor::getActorId, Function.identity()));
//
//        List<ConnectionUrlParser.Pair<CheckMgtActor, CheckMgtFilmActor>> innerJoinList = lcma2.stream()
//                .filter(it -> actorIdMap.containsKey(it.getActorId()))
//                .map(it -> TestPropertyValues.Pair.of(String.valueOf(it.getActorId()), String.valueOf(actorIdMap.get(it.getActorId()))))
//                //.map(it -> TestPropertyValues.Pair.of(it, actorIdMap.get(it.getActorId())))
//                .collect(Collectors.toList());


//        Map<Long, User> userIdMap = userList.stream()
//                .collect(Collectors.toMap(User::getUserId, Function.identity()));

//        List<Pair<Order, User,>> innerJoinList = orderList.stream()
//                .filter(it -> userIdMap.containsKey(it.getUserId()))
//                .map(it -> Pair.of(it, userIdMap.get(it.getUserId())))
//                .collect(Collectors.toList());




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

        System.out.println(lcfaVo.size());

        for( CheckMgtFilmActorVO  vo : lcfaVo){
            System.out.println(vo.getActorId());
            System.out.println(vo.getFilmId());
        }




        //checkMgtService.insertFilmActorCopied(lcfaVo);

        //productTransactionManager.rollback(status);
        //productTransactionManager.commit(status);

    }


    @Test
    void testBusinessTaskletTest3() throws Exception
    {
        int max = 10;
        productTransactionManager = adminDBConfiguration.productTransactionManager(this.adminDBConfiguration.db2DataSource());
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);  // (3)
        TransactionStatus status = productTransactionManager.getTransaction(definition);



        int limit = 10;
        int offset = 0;

        Date date = new Date();
        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(transFormat.format(date));
        LocalDate yesterday = today.plusDays(-1);

        today = today.plusDays(2);

        System.out.println("LocalDate after " + " adding days: " + yesterday);
        System.out.println("LocalDate after " + " adding days: " + today);


        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("today", today);
        map.put("yesterday", yesterday);
        map.put("limit", limit);
        map.put("offset", offset);

        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);


        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();


        List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());
        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);

        List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());
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

        //productTransactionManager.rollback(status);
        productTransactionManager.commit(status);

    }



/*

    @Test
    void testBusinessTaskletTest4() throws Exception {

//tradeAdminTX



        productTransactionManager = adminDBConfiguration.productTransactionManager(this.adminDBConfiguration.db2DataSource());


        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
//
//
        definition.setPropagationBehavior(TransactionDefinition
                .PROPAGATION_REQUIRES_NEW);  // (3)
//
//
        TransactionStatus status;

        status = productTransactionManager.getTransaction(definition);


        status.setRollbackOnly();

        productTransactionManager.rollback(status);


        System.out.println("aaaaaaaaaaaaaaaaaaa");



        //공통모듈로 만들기!!

        Date date = new Date();
        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(transFormat.format(date));
        LocalDate returnvalue_to = today.plusDays(-1);
        System.out.println("LocalDate after " + " adding days: " + today);
        System.out.println("LocalDate after " + " adding days: " + returnvalue_to);

//        System.out.println("현재시간 : " + new Date(cal1.));
//
//        Date date = new Date(cal1.getTimeInMillis());
//        System.out.println("연산시간 : " + date);

        String date_str = "2021-03-01 11:11:11";
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = transFormat.parse(date_str);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date); // 시간 설정
        cal1.add(Calendar.YEAR, 2); // 년 연산
        cal1.add(Calendar.MONTH, 4); // 월 연산
        cal1.add(Calendar.DATE, 4); // 일 연산
        cal1.add(Calendar.HOUR_OF_DAY , 4); // 시간 연산
        cal1.add(Calendar.MINUTE, 5); // 분 연산
        cal1.add(Calendar.SECOND, 12); // 초 연산

        System.out.println("설정시간 : " + date);
        System.out.println("연산시간 : " + new Date(cal1.getTimeInMillis()));

    }
*/

    //}






//    @Test
//    void testBusinessTaskletTest2() throws Exception{
//
//
//        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor();
//        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor();
//
//        Set<CheckMgtFilmActor> arr2 = new HashSet<>(lcfa2);
//        ArrayList<CheckMgtFilmActor> resArr2 = new ArrayList<>(arr2);
//
//        System.out.println("~~~~~~~~~~~1");
//        System.out.println(resArr2.size());
//        System.out.println("~~~~~~~~~~~2");
//
//        List<Integer> newList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());
//
//        System.out.println("~~~~~~~~~~~3");
//
//
//
//        List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(newList);
//
//
//
//
//
//
//        for (int i=0; i< newList.size();i++)
//        {
//            System.out.println(newList.get(i));
//        }
//
//        System.out.println("~~~~~~~~~~~4");
//
//        System.out.println("중복체크!!start");
//        //System.out.println(lcfa2.stream().distinct().collect(Collectors.groupingBy(s ->s.getFilmId())));
//        System.out.println(lcfa2.stream().collect(Collectors.groupingBy(s-> s.getFilmId())));
//        System.out.println("중복체크!!end");
//
//
//        //resultList = dataList.parallelStream().distinct().collect(Collectors.toList());
//
//        //List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(newList);
//        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();
//
//        //총 5462 건 이고
//        //CheckMgtFilmActor 자체를 10개씩 끊어서 가져오면..
//        //10건에 맞추어 cma, cf 건 별도로 마스터 가지고 오기!!
//        //CheckMgtFilmActor 에서 cma 따로 따로 미리 구해두기
//        //CheckMgtFilmActor 에서 cf 따로 따로 미리 구해두기
//
//
//        //전제조건
//
//
//        for(CheckMgtFilmActor  cfa  : lcfa2)
//        {
//            for(CheckMgtActor  cma : lcma2)
//            {
//                if(cfa.getActorId() == cma.getActorId())
//                {
//
//                    for(CheckMgtFilm cf : lcf2)
//                    {
//                        if(cf.getFilmId() == cfa.getFilmId())
//                        {
//                            CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(cma.getActorId()
//                                    , cma.getFirstName()
//                                    , cma.getLastName()
//                                    , cf.getFilmId()
//                                    , cf.getTitle()
//                                    , cf.getDescription()
//                                    , cf.getRelease_year()
//                                    , cma.getLastUpdate()
//                            );
//
//                            lcfaVo.add(cmf);
//                        }
//                    }
//                }
//            }
//
//
//        }
//
//        System.out.println("gogogogo size");
//        System.out.println(lcfaVo.size());
//
//        //checkMgtService.insertFilmActorCopied(lcfaVo);
//
//
//
//    }



    @Test
    void testBusinessTaskletTest() throws Exception{


        //System.out.println("중복체크!!start");
        //System.out.println(lcfa2.stream().distinct().collect(Collectors.groupingBy(s ->s.getFilmId())));
        //System.out.println(lcfa2.stream().collect(Collectors.groupingBy(s-> s.getFilmId())));
        //System.out.println("중복체크!!end");


        //        Set<CheckMgtFilmActor> arr2 = new HashSet<>(lcfa2);
//        ArrayList<CheckMgtFilmActor> resArr2 = new ArrayList<>(arr2);

        //전체 건수..
        //loop 돌면서 처리해야하는 경우..
        //

//        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor();
//        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor();
//        List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm();
//        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();
//
//        for(CheckMgtActor  cma  : lcma2)
//        {
//            for(CheckMgtFilmActor  cfa : lcfa2)
//            {
//                if(cfa.getActorId() == cma.getActorId())
//                {
//                    for(CheckMgtFilm cf : lcf2)
//                    {
//                        if(cf.getFilmId() == cfa.getFilmId())
//                        {
//                            CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(cma.getActorId()
//                                    , cma.getFirstName()
//                                    , cma.getLastName()
//                                    , cf.getFilmId()
//                                    , cf.getTitle()
//                                    , cf.getDescription()
//                                    , cf.getRelease_year());
//
//                            lcfaVo.add(cmf);
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println("lcfaVo size");
//        System.out.println(lcfaVo.size());
//
//        checkMgtService.insertCheckMgtFilmActorVO(lcfaVo);



        //건수 추출 시!!!
        //List<Integer> newList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());

    }






/*

    @Test
    void testBusinessTaskletTest5() throws Exception
    {
        //아래 Map으로 호출
        //그리고 페이징 사이즈는
        //전체 사이즈를 한번 구하고
        int max = 10;

        //
//        for (int i= 0; i <= max; i++)
//        {

        productTransactionManager = adminDBConfiguration.productTransactionManager(this.adminDBConfiguration.db2DataSource());

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);  // (3)
        TransactionStatus status = productTransactionManager.getTransaction(definition);


        int from_page = 0;
        int to_page = 10;

        Date date = new Date();
        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(transFormat.format(date));
        LocalDate yesterday = today.plusDays(-1);

        System.out.println("LocalDate after " + " adding days: " + yesterday);
        System.out.println("LocalDate after " + " adding days: " + today);


        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor();
        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();


        List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());
        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);

        List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());
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

        productTransactionManager.rollback(status);
        //productTransactionManager.commit(status);

    }

*/




}












//@Component()
//public class SalesPlanChunkTranTask implements Tasklet {
//
//    @Inject
//    ItemStreamReader<SalesPlanDetail> itemReader;
//
//    // (2)
//    @Inject
//    @Named("jobTransactionManager")
//    PlatformTransactionManager transactionManager;
//
//    @Inject
//    SalesPlanDetailRepository repository;
//
//    private static final int CHUNK_SIZE = 10;
//
//    @Override
//    public RepeatStatus execute(StepContribution contribution,
//                                ChunkContext chunkContext) throws Exception {
//
//        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
//        definition.setPropagationBehavior(TransactionDefinition
//                .PROPAGATION_REQUIRES_NEW);  // (3)
//        TransactionStatus status = null;
//
//        try {
//            // omitted
//
//            itemReader.open(executionContext);
//
//            while ((item = itemReader.read()) != null) {
//
//                if (count % CHUNK_SIZE == 0) {
//                    status = transactionManager.getTransaction(definition); // (4)
//                }
//                count++;
//
//                // omitted
//
//                repository.create(item);
//                if (count % CHUNK_SIZE == 0) {
//                    transactionManager.commit(status);  // (5)
//                }
//            }
//        } catch (Exception e) {
//            logger.error("Exception occurred while reading.", e);
//            transactionManager.rollback(status);    // (6)
//            throw e;
//        } finally {
//            if (!status.isCompleted()) {
//                transactionManager.commit(status);   // (7)
//            }
//            itemReader.close();
//        }
//
//        return RepeatStatus.FINISHED;
//    }
//}
















