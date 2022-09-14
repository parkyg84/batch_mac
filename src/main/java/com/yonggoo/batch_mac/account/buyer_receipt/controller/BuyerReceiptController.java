package com.yonggoo.batch_mac.account.buyer_receipt.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
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

    //잡실행!!!
    //https://velog.io/@ehdrms2034/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B0%B0%EC%B9%98-Job-%EC%84%A4%EC%A0%95%EA%B3%BC-%EC%8B%A4%ED%96%89


    @Autowired
    @Qualifier("buyerJob")
    public Job job2;


    private final JobLauncher jobLauncher;

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



}
