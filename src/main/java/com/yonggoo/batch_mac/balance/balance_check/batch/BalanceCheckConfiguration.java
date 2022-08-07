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
import org.springframework.batch.core.configuration.annotation.JobScope;
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
    private final BatchPersonItemReader batchPersonItemReader;
    private final BatchPersonItemWriter batchPersonItemWriter;

    private static final int chunkSize = 30;

//    @Value("${chunkSize:1000}")


    public BalanceCheckConfiguration(JobBuilderFactory jobBuilderFactory
                                   , StepBuilderFactory stepBuilderFactory
                                    , BatchPersonItemReader batchPersonItemReader
                                    , BatchPersonItemWriter batchPersonItemWriter)
    {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.batchPersonItemReader = batchPersonItemReader;
        this.batchPersonItemWriter = batchPersonItemWriter;
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
    @Qualifier("balanceCheckJob")
    public Job balanceCheckJob() throws Exception {

        System.out.println("balanceCheckJob start");


                return jobBuilderFactory.get("balanceCheckJob")
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


        System.out.println("balanceCheckJob step 1 start");
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(chunkSize)
                .reader(batchPersonItemReader.tradeDbRead())
                .processor(processor())
               .writer(batchPersonItemWriter.writer())
                .build();
    }


    @Bean
    public Step step2() throws  Exception  {

        System.out.println("balanceCheckJob step 2 start");

       return stepBuilderFactory.get("step2")
                .<Person, Person> chunk(chunkSize)
                .reader(batchPersonItemReader.tradeDbRead())
                .processor(processor())
                .writer(batchPersonItemWriter.writer())
                .build();
    }

    @Bean
    public Step step3() throws  Exception  {

        System.out.println("balanceCheckJob step 3 start");

        return stepBuilderFactory.get("step3")
                .<Person, Person> chunk(chunkSize)
                .reader(batchPersonItemReader.tradeDbRead())
                .processor(processor())
                .writer(batchPersonItemWriter.writer())
                .build();
    }

    @Bean
    public Step step4() throws  Exception  {

        return stepBuilderFactory.get("step4")
                .<Person, Person> chunk(chunkSize)
                .reader(batchPersonItemReader.tradeDbRead())
                .processor(processor())
                .writer(batchPersonItemWriter.writer())
                .build();
    }

}



//샘플
// https://youtu.be/hr2XTbKSdAQ


//https://hevodata.com/learn/spring-batch-jobs/   job url 호출하는것!!

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


//    @Bean
//    @Qualifier("KakaoWebClient")
//    public WebClient kakaoWebClient(ObjectMapper baseConfig) {
//        ExchangeStrategies exchangeStrategies = getExchangeStrategies(baseConfig);
//
//        return WebClient.builder()
//                .baseUrl("https://dapi.kakao.com")
//                .exchangeStrategies(exchangeStrategies)
//                .defaultHeader("Authorization", "KakaoAK " + SecretKey.KAKAO_API_KEY)
//                .build();
//    }

//@Configuration
//public class LocationRequestConfig {
//
//    private final WebClient kakaoWebClient;
//
//    public LocationRequestConfig(@Qualifier("KakaoWebClient") WebClient kakaoWebClient) {
//        this.kakaoWebClient = kakaoWebClient;
//    }
//
//    @Bean
//    public Requesters requesters() {
//        return new Requesters(kakaoWebClient);
//    }
//}


//    https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-autowired-annotation-qualifiers


//
//    @Bean
//    @JobScope
//    public Step step1(@Qualifier("myBatisPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader
//            ,  @Qualifier("myBatisBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter
//    ) throws  Exception  {
//
//        return stepBuilderFactory.get("step1")
//                .<Person, Person> chunk(chunkSize)
//                .reader(myBatisPagingItemReader)
//                .processor(processor())
//                .writer(myBatisBatchItemWriter)
//                .build();
//    }


//
//    @Bean
//    public Step step2(@Qualifier("myBatisCustomerPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader
//            ,  @Qualifier("myBatisCustomerBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter
//    ) throws  Exception  {
//
//        return stepBuilderFactory.get("step2")
//                .<Customer, Customer> chunk(chunkSize)
//                .reader(myBatisPagingItemReader)
//                .writer(myBatisBatchItemWriter)
//                .build();
//    }





//
//    @Bean
//    public Step step3(@Qualifier("myBatisPaymentPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader
//            ,  @Qualifier("myBatisPaymentBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter
//    ) throws  Exception  {
//
//        return stepBuilderFactory.get("step3")
//                .<Payment, Payment> chunk(chunkSize)
//                .reader(myBatisPagingItemReader)
//                .processor(paymentProcessor())
//       o         .writer(myBatisBatchItemWriter)
//                .build();
//    }




//    @Override
//    protected JobLauncher createJobLauncher() throws Exception {
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(jobRepository);
//        jobLauncher.afterPropertiesSet();
//        return jobLauncher;
//    }



//  .preventRestart() //재시작 방지