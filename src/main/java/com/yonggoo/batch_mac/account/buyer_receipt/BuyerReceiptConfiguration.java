package com.yonggoo.batch_mac.account.buyer_receipt;
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

@Configuration
@Slf4j
@Qualifier("buyerReceiptConfiguration")
@RequiredArgsConstructor
public class BuyerReceiptConfiguration {

    private static final int chunkSize = 30;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CheckMgtService checkMgtService;

    @Bean(name="buyerJob")
    public Job buyerReceiptJob() throws Exception {
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
                    checkMgtService.testStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step BuyReceiptStep2(){
        return stepBuilderFactory.get("buyerReceiptJobStep2")
                .tasklet((contribution, chunkContext) -> {
                    checkMgtService.testStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step BuyReceiptStep3(){
        return stepBuilderFactory.get("buyerReceiptJobStep3")
                .tasklet((contribution, chunkContext) -> {
                    checkMgtService.testStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


    @Bean
    public Step BuyReceiptStep4(){
        return stepBuilderFactory.get("buyerReceiptJobStep4")
                .tasklet((contribution, chunkContext) -> {
                    checkMgtService.testStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


    @Bean
    public Step BuyReceiptStep5(){
        return stepBuilderFactory.get("buyerReceiptJobStep5")
                .tasklet((contribution, chunkContext) -> {
                    checkMgtService.testStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step BuyReceiptStep6(){
        return stepBuilderFactory.get("buyerReceiptJobStep6")
                .tasklet((contribution, chunkContext) -> {
                    checkMgtService.testStep();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
