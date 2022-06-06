package com.yonggoo.batch_mac.balance.balance_check.batch;

import com.yonggoo.batch_mac.balance.balance_check.model.Customer;
import com.yonggoo.batch_mac.balance.balance_check.model.Payment;
import com.yonggoo.batch_mac.balance.balance_check.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class BatchPersonItemWriter {

    @Bean(name ="myBatisBatchItemWriter")
    @StepScope
    public MyBatisBatchItemWriter<Person> writer(@Qualifier("mySqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        MyBatisBatchItemWriter<Person> myBatisBatchItemWriter = new MyBatisBatchItemWriter<Person>();
        myBatisBatchItemWriter.setSqlSessionFactory(sqlSessionFactory);
        myBatisBatchItemWriter.setStatementId("insertTest");
        return myBatisBatchItemWriter;
    }



    @Bean(name ="myBatisCustomerBatchItemWriter")
    @StepScope
    public MyBatisBatchItemWriter<Customer> customerWriter(@Qualifier("mySqlAdminSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        MyBatisBatchItemWriter<Customer> myBatisBatchItemWriter = new MyBatisBatchItemWriter<Customer>();
        myBatisBatchItemWriter.setSqlSessionFactory(sqlSessionFactory);
        myBatisBatchItemWriter.setStatementId("insertCustomer");
        return myBatisBatchItemWriter;
    }


    @Bean(name ="myBatisPaymentBatchItemWriter")
    @StepScope
    public MyBatisBatchItemWriter<Payment> paymentWriter(@Qualifier("mySqlAdminSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        MyBatisBatchItemWriter<Payment> myBatisBatchItemWriter = new MyBatisBatchItemWriter<Payment>();
        myBatisBatchItemWriter.setSqlSessionFactory(sqlSessionFactory);
        myBatisBatchItemWriter.setStatementId("insertPayment");
        return myBatisBatchItemWriter;
    }

}
