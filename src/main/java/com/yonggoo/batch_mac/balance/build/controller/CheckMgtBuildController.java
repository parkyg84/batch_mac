package com.yonggoo.batch_mac.balance.build.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/job/balance/build/")
public class CheckMgtBuildController {

//    @Autowired
//    public JobLauncher jobLauncher;
//
    @Autowired
    @Qualifier("checkJob")
    public Job job1;


    private final JobLauncher jobLauncher;


    @RequestMapping(value="/start")
    public String launch() {
        try {

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            return jobLauncher.run(job1, jobParameters).getStatus().toString();
        }
        catch (Exception ex){
            return ex.getMessage().toString();
        }
    }


}
