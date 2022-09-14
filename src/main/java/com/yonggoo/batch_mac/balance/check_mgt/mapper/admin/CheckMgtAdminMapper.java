package com.yonggoo.batch_mac.balance.check_mgt.mapper.admin;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Map;

@Component
@Mapper
public interface CheckMgtAdminMapper {
    public int selectCheckAll() throws Exception;

    public void insert(Map<String, Object> args) throws Exception;


    public void insertFilmActorCopied(Map<String, Object> args) throws Exception;


    public void insertTestStep(Map<String, Object> args) throws Exception;

}
