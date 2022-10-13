package com.yonggoo.batch_mac.account.buyer_receipt.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/job/account/buyreceipt/")
public class BuyerReceiptController {

    @Autowired
    @Qualifier("buyerJob")
    public Job job2;

    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;
    private final JobOperator jobOperator;


    @RequestMapping(value="/start")
    public String launch() {
        try {

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            return jobLauncher.run(job2, jobParameters).getStatus().toString();
        }
        catch (Exception ex){
            return ex.getMessage().toString();
        }
    }






    @RequestMapping(value="/stop")
    @ResponseBody
    public String stopBatchJobs() throws Exception {

        String returnMsg = "";

        try {
            Set<JobExecution> jobExecutionsSet = jobExplorer.findRunningJobExecutions("buyerJob");

            for (JobExecution jobExecution : jobExecutionsSet) {

                if (jobExecution.getStatus() == BatchStatus.STARTED || jobExecution.getStatus() == BatchStatus.STARTING) {
                    jobOperator.stop(jobExecution.getId());

                    returnMsg = jobExecution.getStatus() + "ID :" + jobExecution.getId();
                }
            }
        }
        catch (Exception ex)
        {

        }

        return returnMsg;
    }

}