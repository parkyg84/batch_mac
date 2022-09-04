package com.yonggoo.batch_mac.balance.check_mgt;


import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilm;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActorVO;
import com.yonggoo.batch_mac.balance.check_mgt.service.CheckMgtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest

public class CheckMgtConfigurationTest {



    @Autowired
   public CheckMgtService checkMgtService;
//
//
//    public CheckMgtConfigurationTest(CheckMgtServiceTest checkMgtServiceTest)
//    {
//            this.checkMgtServiceTest = checkMgtServiceTest;
//    }


//    @Test
//    void testJoin() throws Exception{
//
//
//        System.out.println("hey hey");
//        System.out.println(checkMgtService.CheckCount());
//        System.out.println("yo yo");
//
////        CheckMgtTradeMapper checkMgtTradeMapper = null;
////
////        checkMgtTradeMapper = new CheckMgtTradeMapper() {
////            @Override
////            public int selectCheckAll() throws Exception {
////                return 0;
////            }
////        };
//
////		CheckMgtService checkMgtService2 = new CheckMgtService(checkMgtTradeMapper2);
//
//
//
////		CheckMgtTradeMapper checkMgtTradeMapper = new CheckMgtTradeMapper() {
////			@Override
////			public int selectCheckAll() throws Exception {
////				return 0;
////			}
////		};
//
//        //CheckMgtService checkMgtService2 = new CheckMgtService(checkMgtTradeMapper);
//
//
//
//        System.out.println("check~~~~~start");
//        //System.out.println(checkMgtService2.CheckCount());
//        System.out.println("check~~~~~end");
//
//
//
//
//
//        List<CheckMgtActor> lcma = new ArrayList<>();
//        List<CheckMgtFilm> lcf = new ArrayList<CheckMgtFilm>();
//        List<CheckMgtFilmActor> lcfa = new ArrayList<CheckMgtFilmActor>();
//        List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();
//
//
//
//
//        for(int i=1; i<11; i++)
//        {
//            CheckMgtActor cma = new CheckMgtActor(i, String.valueOf(i)+"_aaa", String.valueOf(i)+"_bbb", LocalDate.now());
//            lcma.add(cma);
//        }
//
//
//        for(int i=1; i<11; i++)
//        {
//            CheckMgtFilmActor cfa = new CheckMgtFilmActor(i, i, LocalDate.now());
//            lcfa.add(cfa);
//        }
//
//        for(int i=1; i<11; i++)
//        {
//            CheckMgtFilm cf = new CheckMgtFilm(i,Integer.toString(i)+"_title", Integer.toString(i)+"_desc", Integer.toString(LocalDate.now().getDayOfYear()));
//            lcf.add(cf);
//        }
//
//
//
//
//        int la_max =lcma.size();
//        int lf_max =lcf.size();
//        int lfa_max =lcfa.size();
//
//
//
//
//        for(int i = 0; i < la_max; i++)
//        {
//            for(int j=0; j < lf_max; j++)
//            {
//
//                if(lcma.get(i).getActorId() == lcfa.get(j).getActorId()) {
//
//                    for (int k = 0; k < lfa_max; k++) {
//
//                        if (lcfa.get(j).getFilmId() == lcf.get(k).getFilmId()) {
//
//                            //System.out.println(lcma.get(i).getActorId());
//
//                            CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(lcma.get(i).getActorId()
//                                    , lcma.get(i).getFirstName()
//                                    , lcma.get(i).getLastName()
//                                    , lcf.get(k).getFilmId()
//                                    , lcf.get(k).getTitle()
//                                    , lcf.get(k).getDescription()
//                                    , lcf.get(k).getReleaseYear());
//
//                            lcfaVo.add(cmf);
//
//                        }
//                    }
//                }
//            }
//        }
//
//
//
//
//        System.out.println("lcfaVo size");
//        System.out.println(lcfaVo.size());
//
//        for( CheckMgtActor  cma  : lcma)
//        {
//            for(CheckMgtFilmActor  cfa : lcfa)
//            {
//                if(cfa.getActorId() == cma.getActorId())
//                {
//
//                    for(CheckMgtFilm cf : lcf)
//                    {
//                        if(cf.getFilmId() == cfa.getFilmId())
//                        {
//
//
//                            CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(cma.getActorId()
//                                    , cma.getFirstName()
//                                    , cma.getLastName()
//                                    , cf.getFilmId()
//                                    , cf.getTitle()
//                                    , cf.getDescription()
//                                    , cf.getRelease_year());
//
//                            lcfaVo.add(cmf);
//
//                        }
//                    }
//                }
//            }
//        }
//
//
//
//
//
//        lcfaVo.forEach(s -> System.out.println(s.getFirstName()));
//        System.out.println("lcfaVo size");
//        System.out.println(lcfaVo.size());
//
//
//
//        //System.out.println(vo.getActorId());
//        //lcfaVo.forEach(s -> System.out.println(s));
//
//
//
//
//
//    }

}
