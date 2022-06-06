package com.yonggoo.batch_mac.balance.balance_check.batch;

import com.yonggoo.batch_mac.balance.balance_check.batch.processor.BatchPersonItemProcessor;
import com.yonggoo.batch_mac.balance.balance_check.batch.processor.PaymentItemProcessor;
import com.yonggoo.batch_mac.balance.balance_check.model.Customer;
import com.yonggoo.batch_mac.balance.balance_check.model.Payment;
import com.yonggoo.batch_mac.balance.balance_check.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
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
public class BalanceCheckConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static final int chunkSize = 30;

//    @Value("${chunkSize:1000}")
//    private int chunkSize;

    public BalanceCheckConfiguration(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public BatchPersonItemProcessor processor() {
        return new BatchPersonItemProcessor();
    }

    @Bean
    public PaymentItemProcessor paymentProcessor() {
        return new PaymentItemProcessor();
    }


    @Bean
    public Job balanceCheckJob(BatchCompletionNotificationListener listener
            , Step step1
            , Step step2
            , Step step3 )
        {
        return jobBuilderFactory.get("balanceCheckJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }


    @Bean
    public Step step1(@Qualifier("myBatisPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader
            ,  @Qualifier("myBatisBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter
    ) throws  Exception  {

        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(chunkSize)
                .reader(myBatisPagingItemReader)
                .processor(processor())
                .writer(myBatisBatchItemWriter)
                .build();
    }



    @Bean
    public Step step2(@Qualifier("myBatisCustomerPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader
            ,  @Qualifier("myBatisCustomerBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter
    ) throws  Exception  {

        return stepBuilderFactory.get("step2")
                .<Customer, Customer> chunk(chunkSize)
                .reader(myBatisPagingItemReader)
                .writer(myBatisBatchItemWriter)
                .build();
    }



    @Bean
    public Step step3(@Qualifier("myBatisPaymentPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader
            ,  @Qualifier("myBatisPaymentBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter
    ) throws  Exception  {

        return stepBuilderFactory.get("step3")
                .<Payment, Payment> chunk(chunkSize)
                .reader(myBatisPagingItemReader)
                .processor(paymentProcessor())
                .writer(myBatisBatchItemWriter)
                .build();
    }


}




//job flow 처리  >> 여기 정리하자!!
//https://jojoldu.tistory.com/328?category=902551


//https://yhmane.tistory.com/209

//    @Bean
//    public Job job() {
//        return this.jobBuilderFactory.get("job")
//                .start(stepA())
//                .on("*")
//                .to(stepB())
//                .from(stepA())
//                .on("FAILED")
//                .to(stepC())
//                .end()
//                .build();
//    }


//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info(">>>>> This is Step3");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }


//    보시는 것처럼 next()는 순차적으로 Step들 연결시킬때 사용됩니다.
//    step1 -> step2 -> stpe3 순으로 하나씩 실행시킬때 next() 는 좋은 방법입니다.
//
//    자 그럼 순차적으로 호출되는지 한번 실행해볼까요?
//    이번에는 Job Parameter를 version=1로 변경하신뒤
