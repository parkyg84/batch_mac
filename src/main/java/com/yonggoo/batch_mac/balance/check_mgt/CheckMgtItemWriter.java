package com.yonggoo.batch_mac.balance.check_mgt;

import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
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

    //https://mkyong.com/spring-batch/spring-batch-unit-test-example/
    @Bean(name ="myBatisBatchItemWriter")
    @StepScope
    public MyBatisBatchItemWriter<CheckMgtPerson> writer() throws Exception
    {


        MyBatisBatchItemWriter<CheckMgtPerson> myBatisBatchItemWriter = new MyBatisBatchItemWriter<CheckMgtPerson>();
        myBatisBatchItemWriter.setSqlSessionFactory(adminDBConfiguration.db2SqlSessionFactory());
        myBatisBatchItemWriter.setStatementId("insertCheckMgt");
        return myBatisBatchItemWriter;

    }


    @Bean(name ="myBatisPagingItemCheckMgtActorWriter")
    @StepScope
    public MyBatisBatchItemWriter<CheckMgtActor> adminDbCheckMgtActorWriter() throws Exception
    {
        // 여기서 읽어서 처리해도 될듯!!

        //list a
        //list b
        //list c
        //list d

        //결과물
        //처리 결과물...아래서..

        //만약 청크단위 처리하다 뻗으면??
        //해당건만 다시 실행해 주면 됨!!


        //전달받은 데이터 기준
        //chunk 기준 join 해서
        // 아래 호출!!


        MyBatisBatchItemWriter<CheckMgtActor> myBatisBatchItemWriter = new MyBatisBatchItemWriter<CheckMgtActor>();
        myBatisBatchItemWriter.setSqlSessionFactory(adminDBConfiguration.db2SqlSessionFactory());
        myBatisBatchItemWriter.setStatementId("insertCheckMgtActor");
        return myBatisBatchItemWriter;


        //https://oingdaddy.tistory.com/325?category=824450

        //mybatis join!!
        //https://creamilk88.tistory.com/157

        
        //스프링배치 강좌
        //https://www.fwantastic.com/p/spring-batch.html
        
        //스프링 배치
        //https://khj93.tistory.com/entry/Spring-Batch%EB%9E%80-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0


        //https://woodcock.tistory.com/29
    }



//    You can implement the driving query pattern. For example in your case:
//
//    The reader performs the first GET request and returns the list of IDs.
//    For each item (ID), a processor performs other REST calls to gather user details (enrich the item) and any necessary information to be written
//    The writer performs the POST request
//    For the processor, you can do all REST calls in the same processor or split them in separate processors and chain them with a CompositeItemProcessor.
//
//            Hope this helps.

}


//https://stackoverflow.com/questions/31820513/spring-boot-spring-batch-multiresourceitemreader-to-merge-files