package com.yonggoo.batch_mac.balance.check_mgt;

import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtPerson;
import com.yonggoo.batch_mac.common.configuration.db.AdminDBConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CheckMgtItemWriter {

    private final AdminDBConfiguration adminDBConfiguration;


    public CheckMgtItemWriter(AdminDBConfiguration adminDBConfiguration) {
        this.adminDBConfiguration = adminDBConfiguration;
    }

    @Bean(name ="myBatisBatchItemWriter")
    @StepScope
    public MyBatisBatchItemWriter<CheckMgtPerson> writer() throws Exception
    {

        MyBatisBatchItemWriter<CheckMgtPerson> myBatisBatchItemWriter = new MyBatisBatchItemWriter<CheckMgtPerson>();
        myBatisBatchItemWriter.setSqlSessionFactory(adminDBConfiguration.db2SqlSessionFactory());
        myBatisBatchItemWriter.setStatementId("insertCheckMgt");
        return myBatisBatchItemWriter;

    }

}
