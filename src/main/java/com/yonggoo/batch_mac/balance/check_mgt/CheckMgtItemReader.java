package com.yonggoo.batch_mac.balance.check_mgt;

import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtPerson;
import com.yonggoo.batch_mac.common.configuration.db.TradeDBConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class CheckMgtItemReader {

    private final TradeDBConfiguration tradeDBConfiguration;

    public CheckMgtItemReader(TradeDBConfiguration tradeDBConfiguration) {
        this.tradeDBConfiguration = tradeDBConfiguration;
    }

    @Bean(name ="myBatisPagingItemReader")
    @StepScope
    public MyBatisPagingItemReader<CheckMgtPerson> tradeDbRead() throws Exception {

        MyBatisPagingItemReader<CheckMgtPerson> myBatisPagingItemReader = new MyBatisPagingItemReader<CheckMgtPerson>();
        Map<String, Object> parameters = new HashMap<>();
        myBatisPagingItemReader.setQueryId("selectCheckMgt");
        myBatisPagingItemReader.setSqlSessionFactory(tradeDBConfiguration.db2SqlSessionFactory());
        myBatisPagingItemReader.setParameterValues(parameters);
        return myBatisPagingItemReader;

    }

}
