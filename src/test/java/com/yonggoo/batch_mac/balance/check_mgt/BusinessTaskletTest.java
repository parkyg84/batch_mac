package com.yonggoo.batch_mac.balance.check_mgt;

import com.yonggoo.batch_mac.common.configuration.db.AdminDBConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;


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


    private static final int CHUNK_SIZE = 10;

    @Autowired
    private AdminDBConfiguration adminDBConfiguration;

    @Autowired
    private PlatformTransactionManager productTransactionManager;


    @Autowired
    private DefaultTransactionDefinition definition;

    
    
    //junit 처리값
    //https://donghyeon.dev/junit/2021/04/11/JUnit5-%EC%99%84%EB%B2%BD-%EA%B0%80%EC%9D%B4%EB%93%9C/


    
//
//    @Test
//    void testBusinessTaskletTest97() throws Exception
//    {
//
//        int limit = 10;
//        int offset = 0;
//
//        Date date = new Date();
//        Calendar cal1 = Calendar.getInstance();
//        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//        LocalDate today = LocalDate.parse(transFormat.format(date));
//        LocalDate yesterday = today.plusDays(-10);
//        today = today.plusDays(10);
//        Map<String,Object> map =  new HashMap<String,Object>() ;
//        map.put("today", today);
//        map.put("yesterday", yesterday);
//        map.put("limit", limit);
//        map.put("offset", offset);
//
//        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);
//        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();
//        System.out.println(lcfa2.size());
//
//        List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());
//        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);
//
//        List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());
//        List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(filmList);
//
//
//
//        List<CheckMgtActor> lcfa = checkMgtService.selectCheckMgtActorAll(map);
//
//
//        Map<Integer, CheckMgtActor> idMap = lcfa.stream()
//                .distinct().collect(Collectors.toMap(CheckMgtActor::getActorId, Function.identity()));
//
//        System.out.println(idMap.size());
//
//
//        //필터 걸어주는것!!
//        List<CheckMgtFilmActor> lcfa22 = lcfa2.stream()
//                .filter(it -> idMap.containsKey(it.getActorId()))
//                .collect(Collectors.toList());
//
//        for(CheckMgtFilmActor vo :lcfa22){
//
//            System.out.println(vo.getActorId());
//            System.out.println(vo.getFilmId());
//
//        }
//
//    }
//
//
//        @Test
//    void testBusinessTaskletTest98() throws Exception
//    {
//        List<User> userList = Lists.newArrayList(User.create(1), User.create(1), User.create(4));
//        List<Order> orderList = Lists.newArrayList(Order.create(1, 99), Order.create(2, 98));
//
//
//
//
////        System.out.println(userList.size());
////        System.out.println(orderList.size());
//
//
//        //inner
////        Map<Long, User> userIdMap = userList.stream()
////                .collect(Collectors.toMap(User::getUserId, Function.identity()));
//
////        List<Pair<Order, User,>> innerJoinList = orderList.stream()
////                .filter(it -> userIdMap.containsKey(it.getUserId()))
////                .map(it -> TestPropertyValues.Pair.of(it, userIdMap.get(it.getUserId())))
////                .collect(Collectors.toList());
//
//
//        //left
//        Map<Integer, Order> orderIdMap = orderList.stream().collect(Collectors.toMap(Order::getOrderId, Function.identity()));
//
//
//        //System.out.println(orderIdMap.get(0).getUserId());
//        //System.out.println(orderIdMap.get(0).getUserId());
//        System.out.println(orderIdMap.get(1).getOrderId());
//        System.out.println(orderIdMap.get(2).getOrderId());
//
//
//        //System.out.println(orderIdMap.get(1).getUserId());
//        //System.out.println(orderIdMap.get(2).getUserId());
//
//
//
//
//        List<User> leftJoinUser = userList.stream()
//                .filter(it -> orderIdMap.containsKey(it.getUserId()))
//                .collect(Collectors.toList());
//
//        for(User vo : leftJoinUser){
//            System.out.println(vo.getUserId());
//        }
//
//
//    }
//
//
//    @Test
//    void testBusinessTaskletTest99() throws Exception
//    {
//        int max = 10;
//        int limit = 10;
//        int offset = 0;
//
//        Date date = new Date();
//        Calendar cal1 = Calendar.getInstance();
//        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//        LocalDate today = LocalDate.parse(transFormat.format(date));
//        LocalDate yesterday = today.plusDays(-10);
//
//        today = today.plusDays(10);
//
//        System.out.println("LocalDate after " + " adding days: " + yesterday);
//        System.out.println("LocalDate after " + " adding days: " + today);
//
//
//        Map<String,Object> map =  new HashMap<String,Object>() ;
//        map.put("today", today);
//        map.put("yesterday", yesterday);
//        map.put("limit", limit);
//        map.put("offset", offset);
//
//        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);
//
//
//        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();
//
//
//        System.out.println(lcfa2.size());
//
//        List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());
//        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);
//
//        List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());
//        List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(filmList);
//
//
//
//        for(CheckMgtFilmActor cfa : lcfa2)
//        {
//            for(CheckMgtActor cma : lcma2)
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
//                                    , cf.getRelease_year()
//                                    , cma.getLastUpdate()
//                            );
//
//                            lcfaVo.add(cmf);
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println(lcfaVo.size());
//
//        for( CheckMgtFilmActorVO  vo : lcfaVo){
//            System.out.println(vo.getActorId());
//            System.out.println(vo.getFilmId());
//        }
//
//    }
//
//
//    @Test
//    void testBusinessTaskletTest3() throws Exception
//    {
//        int max = 10;
//        productTransactionManager = adminDBConfiguration.productTransactionManager(this.adminDBConfiguration.db2DataSource());
//        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);  // (3)
//        TransactionStatus status = productTransactionManager.getTransaction(definition);
//
//
//
//        int limit = 10;
//        int offset = 0;
//
//        Date date = new Date();
//        Calendar cal1 = Calendar.getInstance();
//        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//        LocalDate today = LocalDate.parse(transFormat.format(date));
//        LocalDate yesterday = today.plusDays(-1);
//
//        today = today.plusDays(2);
//
//        System.out.println("LocalDate after " + " adding days: " + yesterday);
//        System.out.println("LocalDate after " + " adding days: " + today);
//
//
//        Map<String,Object> map =  new HashMap<String,Object>() ;
//        map.put("today", today);
//        map.put("yesterday", yesterday);
//        map.put("limit", limit);
//        map.put("offset", offset);
//
//        List<CheckMgtFilmActor> lcfa2 = checkMgtService.selectCheckMgtFilmActor(map);
//
//
//        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();
//
//
//        List<Integer> actorList  = lcfa2.stream().map(CheckMgtFilmActor::getActorId).distinct().collect(Collectors.toList());
//        List<CheckMgtActor> lcma2 = checkMgtService.selectCheckMgtActor(actorList);
//
//        List<Integer> filmList  = lcfa2.stream().map(CheckMgtFilmActor::getFilmId).distinct().collect(Collectors.toList());
//        List<CheckMgtFilm> lcf2 = checkMgtService.selectCheckMgtFilm(filmList);
//
//        for(CheckMgtFilmActor cfa : lcfa2)
//        {
//            for(CheckMgtActor cma : lcma2)
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
//                                    , cf.getRelease_year()
//                                    , cma.getLastUpdate()
//                            );
//
//                            lcfaVo.add(cmf);
//                        }
//                    }
//                }
//            }
//        }
//
//        checkMgtService.insertFilmActorCopied(lcfaVo);
//        productTransactionManager.commit(status);
//
//    }
//
//    @Test
//    void testBusinessTaskletTest() throws Exception{
//    }

}










