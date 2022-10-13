package com.yonggoo.batch_mac.balance.check_mgt;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//mapper mybatis 사용법
//https://veneas.tistory.com/entry/Spring-Boot-MyBatis-%EC%82%AC%EC%9A%A9%EB%B2%95-Mapper-MapperScan
//https://data-make.tistory.com/748
//https://renuevo.github.io/spring/batch/spring-batch-chapter-3/

@Configuration
@RequiredArgsConstructor
@Qualifier("CheckMgtConfiguration")
@Slf4j
public class CheckMgtConfiguration {


    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;
    public final CheckMgtService checkMgtService;

    private static final int chunkSize = 30;




    @Bean(name = "checkCollectJob")
    public Job checkMgtJob() throws Exception {
        return jobBuilderFactory.get("checkCollectJob")
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .listener(new CheckMgtBatchCompletionNotificationListener())
                .start(TaskStep1())
                .next(TaskStep2())
                .next(TaskStep3())
                .next(TaskStep4())
                .next(TaskStep5())
                .next(TaskStep6())
                .next(TaskStep7())
                .build();
    }



    @Bean
    public Step TaskStep1(){
        return stepBuilderFactory.get("taskletStep1")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("taskletStep1");
                    checkMgtService.insertTestStep(1);
                          return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step TaskStep2(){
        return stepBuilderFactory.get("taskletStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("taskletStep2");
                    checkMgtService.insertTestStep(2);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step TaskStep3(){
        return stepBuilderFactory.get("taskletStep3")
                .tasklet((contribution, chunkContext) -> {

                    System.out.println("taskletStep3");
                    checkMgtService.insertTestStep(3);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step TaskStep4(){
        return stepBuilderFactory.get("taskletStep4")
                .tasklet((contribution, chunkContext) -> {





                    System.out.println("taskletStep4");
                    checkMgtService.insertTestStep(4);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step TaskStep5() throws Exception{
        return stepBuilderFactory.get("taskletStep5")
                .tasklet((contribution, chunkContext) -> {

                    System.out.println("taskletStep5");

                    checkMgtService.insertTestStep(5);


                    return RepeatStatus.FINISHED;
                })
                .build();
    }




    @Bean
    public Step TaskStep6(){
        return stepBuilderFactory.get("taskletStep6")
                .tasklet((contribution, chunkContext) -> {

                    System.out.println("taskletStep6");
                    checkMgtService.insertTestStep(6);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


    @Bean
    public Step TaskStep7(){
        return stepBuilderFactory.get("taskletStep7")
                .tasklet((contribution, chunkContext) -> {

                    System.out.println("taskletStep7");
                    checkMgtService.insertTestStep(7);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}