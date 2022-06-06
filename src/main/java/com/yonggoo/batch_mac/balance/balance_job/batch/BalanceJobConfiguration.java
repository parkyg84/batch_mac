package com.yonggoo.batch_mac.balance.balance_job.batch;


import com.yonggoo.batch_mac.balance.balance_check.batch.BatchCompletionNotificationListener;
import com.yonggoo.batch_mac.balance.balance_check.model.Person;
import com.yonggoo.batch_mac.balance.balance_job.batch.processor.BatchJobPersonItemProcessor;
import com.yonggoo.batch_mac.balance.balance_job.model.PersonNew;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class BalanceJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static final int chunkSize = 30;

    public BalanceJobConfiguration(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }


    @Bean
    public BatchJobPersonItemProcessor processNew() {
        return new BatchJobPersonItemProcessor();
    }

    @Bean
    public Job importUserJobNew(BatchCompletionNotificationListener listener, Step step1New) {
        return jobBuilderFactory.get("importUserJob4")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1New)
                .end()
                .build();
    }



    @Bean
    public Step step1New(@Qualifier("myBatisAdminPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader
            ,  @Qualifier("myBatisAdminBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter
    ) throws  Exception  {

        return stepBuilderFactory.get("step4")
                .<PersonNew, PersonNew> chunk(30)
                .reader(myBatisPagingItemReader)
                .processor(processNew())
                .writer(myBatisBatchItemWriter)
                .build();
    }




    @Bean(name ="myBatisAdminPagingItemReader")
    @StepScope
    public MyBatisPagingItemReader<Person> tradeDbRead(@Qualifier("mySqlSqlSessionFactory") SqlSessionFactory db2SqlSessionFactory) {

        MyBatisPagingItemReader<Person> myBatisPagingItemReader = new MyBatisPagingItemReader<Person>();
        Map<String, Object> parameters = new HashMap<>();
        myBatisPagingItemReader.setQueryId("selectTestNew");
        myBatisPagingItemReader.setSqlSessionFactory(db2SqlSessionFactory);
        myBatisPagingItemReader.setParameterValues(parameters);
        return myBatisPagingItemReader;
    }


    @Bean(name ="myBatisAdminBatchItemWriter")
    @StepScope
    public MyBatisBatchItemWriter<Person> writer(@Qualifier("mySqlAdminSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        MyBatisBatchItemWriter<Person> myBatisBatchItemWriter = new MyBatisBatchItemWriter<Person>();
        myBatisBatchItemWriter.setSqlSessionFactory(sqlSessionFactory);
        myBatisBatchItemWriter.setStatementId("insertTestNew");
        return myBatisBatchItemWriter;
    }

}
