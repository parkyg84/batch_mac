package com.yonggoo.batch_mac.controller;

import com.yonggoo.batch_mac.balance.balance_job.batch.BalanceJobConfiguration;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


//https://devfunny.tistory.com/688
//https://mangdan.github.io/spring-batch-adw-2/

@RestController
@RequestMapping(value="/job")
public class JobController {

    private final Job job;
    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;
    private final JobOperator jobOperator;


    public JobController(Job job, JobLauncher jobLauncher, JobExplorer jobExplorer, JobOperator jobOperator)
    {
        this.job = job;
        this.jobLauncher = jobLauncher;
        this.jobExplorer = jobExplorer;
        this.jobOperator = jobOperator;
    }

//    @Qualifier("balanceCheckJob")
//    Job balanceCheckJob;


    @RequestMapping(value="/start")
    @ResponseBody
    public String startBatchJobs() throws JobParametersInvalidException
            , JobExecutionAlreadyRunningException
            , JobRestartException
            , JobInstanceAlreadyCompleteException
    {

        JobExecution jobExecution = null;

        Long jobId;

        Set<JobExecution> jobExecutionsSet = jobExplorer.findRunningJobExecutions("balanceCheckJob");

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobExecution = jobLauncher.run(job, jobParameters);


        return "Good";
    }






    @RequestMapping(value="/stop")
    @ResponseBody
    public String stopBatchJobs() throws Exception {
        Set<JobExecution> jobExecutionsSet = jobExplorer.findRunningJobExecutions("balanceCheckJob");
        for (JobExecution jobExecution : jobExecutionsSet) {

            if (jobExecution.getStatus() == BatchStatus.STARTED || jobExecution.getStatus() == BatchStatus.STARTING) {
                jobOperator.stop(jobExecution.getId());
                System.out.println( jobExecution.getStatus() + "ID :" + jobExecution.getId());
            }
        }

        return "Cancled";
    }




    @Bean
    TaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }



    //비동기 실행 처리
//    @Bean
//    public SimpleJobLauncher simpleJobLauncher(){
//
//        System.out.println("started SimpleJobLauncher");
//
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(jobRepository);
//        jobLauncher.setTaskExecutor(simpleAsyncTaskExcutor());
//
//        return jobLauncher;
//
//    }
//
//    public SimpleAsyncTaskExecutor simpleAsyncTaskExcutor(){
//
//        System.out.println("started SimpleAsyncTaskExecutor");
//
//        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        simpleAsyncTaskExecutor.setConcurrencyLimit(10);
//        return simpleAsyncTaskExecutor;
//
//    }


//    @RequestMapping(value = "/adw/1.0/aptTrade/{operation}", method = RequestMethod.GET)
//    // @Scheduled(cron = "${batch.cron}")
//    public String performRealEstateTradeJob(@PathVariable("operation") String operation) throws Exception {
//        String result = "";
//
//        if (month.equals("")) {
//            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
//            Date time = new Date();
//            month = format1.format(time);
//        }
//
//        switch (operation) {
//            case "startjob": {
//                Long jobId;
//                //BatchStatus jobStatus;
//                Set<JobExecution> jobExecutionsSet = jobExplorer.findRunningJobExecutions("국토부 전국 부동산 실거래가 데이터 수집");
//
//                if (jobExecutionsSet.size() > 0) {
//                    for (JobExecution jobExecution : jobExecutionsSet) {
//                        jobId = jobExecution.getId();
//                        //jobStatus = jobExecution.getStatus();
//
//                        result = "[오류][jobId:" + Long.toString(jobId)
//                                + "] 국토부 전국 부동산 실거래가 데이터 수집 배치작업이 진행중입니다. \n 종료 후 다시 시작하십시요.";
//                    }
//                } else {
//                    startBatchJobs(month);
//                    result = "[정상] 국토부 전국 부동산 실거래가 데이터 수집 배치작업이 시작되었습니다.";
//                }
//                break;
//            }
//            case "stopjob": {
//                stopBatchJobs();
//                result = "[정상] 국토부 전국 부동산 실거래가 데이터 수집 배치작업이 종료되었습니다.";
//                break;
//            }
//            default: {
//                result = "Path 파라미터를 확인해주세요. (start/stop/startjob/stopjob)";
//            }
//        }
//        return result;
//    }

}