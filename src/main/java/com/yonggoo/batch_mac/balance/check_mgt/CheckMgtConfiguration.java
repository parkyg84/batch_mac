package com.yonggoo.batch_mac.balance.check_mgt;


import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtPerson;
import com.yonggoo.batch_mac.balance.check_mgt.processor.ChekMgtItemProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class CheckMgtConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CheckMgtItemReader checkMgtItemReader;
    private final CheckMgtItemWriter checkMgtItemWriter;

    private static final int chunkSize = 30;

    public CheckMgtConfiguration(JobBuilderFactory jobBuilderFactory
            , StepBuilderFactory stepBuilderFactory
            , CheckMgtItemReader checkMgtItemReader
            , CheckMgtItemWriter checkMgtItemWriter)
    {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.checkMgtItemReader = checkMgtItemReader;
        this.checkMgtItemWriter = checkMgtItemWriter;
    }


    @Bean
    public ChekMgtItemProcessor processor() {
        return new ChekMgtItemProcessor();
    }

    @Bean
    @Qualifier("checkMgtJob")
    public Job balanceCheckJob() throws Exception {
        System.out.println("balanceCheckJob start");
        return jobBuilderFactory.get("checkMgtJob")
                .incrementer(new RunIdIncrementer())
                //.listener(listener)
                .start(step1())
//                       .next(step2())
//                        .next(step3())
//                        .next(step4())
                .build();
    }


    @Bean
    public Step step1() throws  Exception  {
        System.out.println("CheckMgtConfiguration step 1 start");
        return stepBuilderFactory.get("step1")
                .<CheckMgtPerson, CheckMgtPerson> chunk(chunkSize)
                .reader(checkMgtItemReader.tradeDbRead())
                .processor(processor())
                .writer(checkMgtItemWriter.writer())
                .build();
    }


    @Bean
    public Step step2() throws  Exception  {

        System.out.println("CheckMgtConfiguration step 2 start");

        return stepBuilderFactory.get("step2")
                .<CheckMgtPerson, CheckMgtPerson> chunk(chunkSize)
                .reader(checkMgtItemReader.tradeDbRead())
                .processor(processor())
                .writer(checkMgtItemWriter.writer())
                .build();
    }

    @Bean
    public Step step3() throws  Exception  {

        System.out.println("CheckMgtConfiguration step 3 start");

        return stepBuilderFactory.get("step3")
                .<CheckMgtPerson, CheckMgtPerson> chunk(chunkSize)
                .reader(checkMgtItemReader.tradeDbRead())
                .processor(processor())
                .writer(checkMgtItemWriter.writer())
                .build();
    }

    @Bean
    public Step step4() throws  Exception  {

        System.out.println("CheckMgtConfiguration step 4 start");

        return stepBuilderFactory.get("step4")
                .<CheckMgtPerson, CheckMgtPerson> chunk(chunkSize)
                .reader(checkMgtItemReader.tradeDbRead())
                .processor(processor())
                .writer(checkMgtItemWriter.writer())
                .build();
    }

}
