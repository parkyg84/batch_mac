package com.yonggoo.batch_mac.account.buyer_receipt;
import com.yonggoo.batch_mac.balance.check_mgt.service.CheckMgtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BuyerReceiptConfiguration {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public CheckMgtService checkMgtService;

    private static final int chunkSize = 30;

    @Bean(name="buyerJob")
    public Job buyerReceiptJob() throws Exception {
        System.out.println("buyerReceiptJob start");
        return jobBuilderFactory.get("buyerJob")
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .listener(new BuyerReceiptCompletionNotificationListener())
                .start(BuyReceiptStep1())
                .next(BuyReceiptStep2())
                .next(BuyReceiptStep3())
                .next(BuyReceiptStep4())
                .next(BuyReceiptStep5())
                .next(BuyReceiptStep6())
                .build();
    }


    @Bean
    public Step BuyReceiptStep1(){
        return stepBuilderFactory.get("buyerReceiptJobStep1")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("buyerReceiptJobStep1");
                    checkMgtService.testStep();

                    return RepeatStatus.FINISHED;

                })
                .build();
    }

    @Bean
    public Step BuyReceiptStep2(){
        return stepBuilderFactory.get("buyerReceiptJobStep2")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("buyerReceiptJobStep2");
                    checkMgtService.testStep();


                    return RepeatStatus.FINISHED;

                })
                .build();
    }

    @Bean
    public Step BuyReceiptStep3(){
        return stepBuilderFactory.get("buyerReceiptJobStep3")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("buyerReceiptJobStep3");
                    checkMgtService.testStep();


                    return RepeatStatus.FINISHED;

                })
                .build();
    }


    @Bean
    public Step BuyReceiptStep4(){
        return stepBuilderFactory.get("buyerReceiptJobStep4")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("buyerReceiptJobStep4");
                    checkMgtService.testStep();


                    return RepeatStatus.FINISHED;

                })
                .build();
    }


    @Bean
    public Step BuyReceiptStep5(){
        return stepBuilderFactory.get("buyerReceiptJobStep5")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("buyerReceiptJobStep5");
                    checkMgtService.testStep();


                    return RepeatStatus.FINISHED;

                })
                .build();
    }




    @Bean
    public Step BuyReceiptStep6(){
        return stepBuilderFactory.get("buyerReceiptJobStep6")
                .tasklet((contribution, chunkContext) -> {


                    System.out.println("buyerReceiptJobStep6");
                    checkMgtService.testStep();


                    return RepeatStatus.FINISHED;

                })
                .build();
    }

}
