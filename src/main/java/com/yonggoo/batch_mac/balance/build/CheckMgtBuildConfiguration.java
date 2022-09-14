package com.yonggoo.batch_mac.balance.build;

import com.yonggoo.batch_mac.balance.check_mgt.CheckMgtItemReader;
import com.yonggoo.batch_mac.balance.check_mgt.CheckMgtItemWriter;
import com.yonggoo.batch_mac.balance.check_mgt.service.CheckMgtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@EnableAutoConfiguration
@Configuration
@Qualifier("checkMgtBuildConfiguration")
@Slf4j
public class CheckMgtBuildConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public CheckMgtService checkMgtService;

    private static final int chunkSize = 30;


    @Bean(name="checkJob")
    public Job checkMgtBuildConfigurationJob() throws Exception {
        System.out.println("CheckMgtBuildConfigurationJob start");
        return jobBuilderFactory.get("checkJob")
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .listener(new CheckMgtBuildCompletionNotificationListener())
                .start(BuildStep1())
                .next(BuildStep2())
                .next(BuildStep3())
                .build();
    }


    @Bean
    public Step BuildStep1(){
        return stepBuilderFactory.get("CheckMgtBuildConfigurationStep1")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("CheckMgtBuildConfigurationStep1");
                    checkMgtService.testStep();

                    return RepeatStatus.FINISHED;

                })
                .build();
    }

    @Bean
    public Step BuildStep2(){
        return stepBuilderFactory.get("CheckMgtBuildConfigurationStep2")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("CheckMgtBuildConfigurationStep2");
                    checkMgtService.testStep();


                    return RepeatStatus.FINISHED;

                })
                .build();
    }

    @Bean
    public Step BuildStep3(){
        return stepBuilderFactory.get("CheckMgtBuildConfigurationStep3")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("CheckMgtBuildConfigurationStep3");
                    checkMgtService.testStep();


                    return RepeatStatus.FINISHED;

                })
                .build();
    }








}