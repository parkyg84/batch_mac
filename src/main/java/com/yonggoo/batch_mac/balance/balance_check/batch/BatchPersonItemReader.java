package com.yonggoo.batch_mac.balance.balance_check.batch;

import com.yonggoo.batch_mac.balance.balance_check.model.Customer;
import com.yonggoo.batch_mac.balance.balance_check.model.Payment;
import com.yonggoo.batch_mac.balance.balance_check.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class BatchPersonItemReader {

    //시나리오
    //테이블 최소 2개 이상 조인
    //사키라 디비로 조인
    //에러 강제발생!!
    //오류 처리방안
    //트랜잭션 처리
    //등등
    //모니터링 방법


    @Bean(name ="myBatisPagingItemReader")
    @StepScope
    public MyBatisPagingItemReader<Person> tradeDbRead(@Qualifier("mySqlSqlSessionFactory") SqlSessionFactory db2SqlSessionFactory) {

        MyBatisPagingItemReader<Person> myBatisPagingItemReader = new MyBatisPagingItemReader<Person>();
        Map<String, Object> parameters = new HashMap<>();
        myBatisPagingItemReader.setQueryId("selectTest");
        myBatisPagingItemReader.setSqlSessionFactory(db2SqlSessionFactory);
        myBatisPagingItemReader.setParameterValues(parameters);
        return myBatisPagingItemReader;
    }

    @Bean(name ="myBatisCustomerPagingItemReader")
    @StepScope
    public MyBatisPagingItemReader<Customer> getCustomerInfo(@Qualifier("mySqlSqlSessionFactory") SqlSessionFactory db2SqlSessionFactory) {

        MyBatisPagingItemReader<Customer> myBatisPagingItemReader = new MyBatisPagingItemReader<Customer>();
        Map<String, Object> parameters = new HashMap<>();
       parameters.put("_pageSize", myBatisPagingItemReader.getPageSize() );
        myBatisPagingItemReader.setQueryId("selectCustomer");
        myBatisPagingItemReader.setSqlSessionFactory(db2SqlSessionFactory);
        myBatisPagingItemReader.setParameterValues(parameters);
        return myBatisPagingItemReader;
    }

    @Bean(name ="myBatisPaymentPagingItemReader")
    @StepScope
    public MyBatisPagingItemReader<Payment> getPaymentInfo(@Qualifier("mySqlSqlSessionFactory") SqlSessionFactory db2SqlSessionFactory) {

        MyBatisPagingItemReader<Payment> myBatisPagingItemReader = new MyBatisPagingItemReader<Payment>();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("_pageSize", myBatisPagingItemReader.getPageSize() );
        myBatisPagingItemReader.setQueryId("selectPayment");
        myBatisPagingItemReader.setSqlSessionFactory(db2SqlSessionFactory);
        myBatisPagingItemReader.setParameterValues(parameters);
        return myBatisPagingItemReader;
    }

}
