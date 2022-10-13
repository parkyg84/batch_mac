package com.yonggoo.batch_mac.balance.build;

import com.yonggoo.batch_mac.balance.check_mgt.CheckMgtItemReader;
import com.yonggoo.batch_mac.balance.check_mgt.CheckMgtItemWriter;
import com.yonggoo.batch_mac.balance.check_mgt.service.CheckMgtService;
import lombok.RequiredArgsConstructor;
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





@Configuration
@Qualifier("checkMgtBuildConfiguration")
@RequiredArgsConstructor
@Slf4j
public class CheckMgtBuildConfiguration {

    private static final int chunkSize = 30;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CheckMgtService checkMgtService;

    @Bean(name="checkJob")
    public Job checkMgtBuildConfigurationJob() throws Exception {
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
                    checkMgtService.testStep();
                    return RepeatStatus.FINISHED;

                })
                .build();
    }

    @Bean
    public Step BuildStep2(){
        return stepBuilderFactory.get("CheckMgtBuildConfigurationStep2")
                .tasklet((contribution, chunkContext) -> {
                    checkMgtService.testStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step BuildStep3(){
        return stepBuilderFactory.get("CheckMgtBuildConfigurationStep3")
                .tasklet((contribution, chunkContext) -> {
                    checkMgtService.testStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}