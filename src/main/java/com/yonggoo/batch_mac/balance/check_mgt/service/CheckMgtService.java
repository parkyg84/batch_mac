package com.yonggoo.batch_mac.balance.check_mgt.service;

import com.yonggoo.batch_mac.balance.check_mgt.mapper.admin.CheckMgtAdminMapper;
import com.yonggoo.batch_mac.balance.check_mgt.mapper.trade.CheckMgtTradeMapper;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilm;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
