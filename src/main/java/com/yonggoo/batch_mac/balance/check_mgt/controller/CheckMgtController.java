package com.yonggoo.batch_mac.balance.check_mgt.controller;

import com.yonggoo.batch_mac.balance.check_mgt.service.CheckMgtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;



@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/job/balance/checkmgt/")
public class CheckMgtController {

    private final CheckMgtService checkMgtService;
    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;
    private final JobOperator jobOperator;

    @Autowired
    @Qualifier("checkCollectJob")
    public Job job3;


   @RequestMapping(value="/start")
    public String launch() {
        try {

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            return jobLauncher.run(job3, jobParameters).getStatus().toString();
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

            Set<JobExecution> jobExecutionsSet = jobExplorer.findRunningJobExecutions("checkCollectJob");

            for (JobExecution jobExecution : jobExecutionsSet) {

                if (jobExecution.getStatus() == BatchStatus.STARTED || jobExecution.getStatus() == BatchStatus.STARTING) {
                    jobOperator.stop(jobExecution.getId());

                    returnMsg = jobExecution.getStatus() + "ID :" + jobExecution.getId();
                }
            }
        }
        catch (Exception ex){

        }

        return returnMsg;
    }


}
