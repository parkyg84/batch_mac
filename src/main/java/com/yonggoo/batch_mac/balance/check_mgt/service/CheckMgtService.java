package com.yonggoo.batch_mac.balance.check_mgt.service;

import com.yonggoo.batch_mac.balance.check_mgt.mapper.admin.CheckMgtAdminMapper;
import com.yonggoo.batch_mac.balance.check_mgt.mapper.trade.CheckMgtTradeMapper;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilm;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CheckMgtService {


    @Autowired
    public CheckMgtTradeMapper checkMgtTradeMapper;

    @Autowired
    public CheckMgtAdminMapper checkMgtAdminMapper;

   public int CheckCount() throws Exception {
        return checkMgtTradeMapper.selectCheckAll();
    }


    public int selectCheckMgtFilmActorAll(Map<String, Object> args) throws Exception {

        return checkMgtTradeMapper.selectCheckMgtFilmActorAll(args);
    }



    public List<CheckMgtActor> selectCheckMgtActor(List<Integer> actorList) throws Exception{

        if (actorList.size() <= 0)
        {
            return null;
        }

        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("list", actorList);



        return checkMgtTradeMapper.selectCheckMgtActor(map);
    }

    public List<CheckMgtFilmActor> selectCheckMgtFilmActor(Map<String, Object> args) throws Exception{

        return checkMgtTradeMapper.selectCheckMgtFilmActor(args);
    }



    public List<CheckMgtActor> selectCheckMgtActorAll(Map<String, Object> args) throws Exception{

        return checkMgtTradeMapper.selectCheckMgtActorAll(args);
    }





    public List<CheckMgtFilm> selectCheckMgtFilm(List<Integer> filmList ) throws Exception{

        if (filmList.size() <= 0)
        {
            return null;
        }

        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("list", filmList);
        return checkMgtTradeMapper.selectCheckMgtFilm(map);
    }


    public void insertCheckMgtFilmActorVO(List<CheckMgtFilmActorVO> list) throws Exception{

        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("list", list);

        checkMgtAdminMapper.insert(map);

    }


    public void insertFilmActorCopied(List<CheckMgtFilmActorVO> list) throws Exception{

        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("list", list);

        checkMgtAdminMapper.insertFilmActorCopied(map);

    }


    public void insertTestStep(int step) throws Exception{

        Map<String,Object> map =  new HashMap<String,Object>() ;
        map.put("step", step);
        checkMgtAdminMapper.insertTestStep(map);


        try {
            System.out.println("Start..." + new Date());
            // delay 5 seconds
            TimeUnit.SECONDS.sleep(30);
            System.out.println("End..." + new Date());

            // delay 0.5 second
            //TimeUnit.MICROSECONDS.sleep(500);

            // delay 1 minute
            //TimeUnit.MINUTES.sleep(1);

        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }

    }






    public void testStep() throws Exception{

        try {
            System.out.println("Start..." + new Date());
            // delay 5 seconds
            TimeUnit.SECONDS.sleep(30);
            System.out.println("End..." + new Date());

            // delay 0.5 second
            //TimeUnit.MICROSECONDS.sleep(500);

            // delay 1 minute
            //TimeUnit.MINUTES.sleep(1);

        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

}
