package com.yonggoo.batch_mac.balance.check_mgt.mapper.trade;


import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilm;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper

public interface CheckMgtTradeMapper {

    public int selectCheckAll() throws Exception;

    public int selectCheckMgtFilmActorAll(Map<String, Object> args) throws Exception;

    public List<CheckMgtActor> selectCheckMgtActor(Map<String, Object> args) throws Exception;


    public List<CheckMgtActor> selectCheckMgtActorAll(Map<String, Object> args) throws Exception;


    public List<CheckMgtFilmActor> selectCheckMgtFilmActor(Map<String, Object> args) throws Exception;

    public List<CheckMgtFilm> selectCheckMgtFilm(Map<String, Object> args) throws Exception;

    public void insert(Map<String, Object> args) throws Exception;





}


//스프링배치 가이드
//https://zzangjava.tistory.com/826