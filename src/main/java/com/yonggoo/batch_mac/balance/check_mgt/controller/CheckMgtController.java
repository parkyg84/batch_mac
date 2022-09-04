package com.yonggoo.batch_mac.balance.check_mgt.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value="/job/balance/checkmgt/collect")
public class CheckMgtController {

    private final Job job;
    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;
    private final JobOperator jobOperator;


    public CheckMgtController(Job job, JobLauncher jobLauncher, JobExplorer jobExplorer, JobOperator jobOperator)
    {
        this.job = job;
        this.jobLauncher = jobLauncher;
        this.jobExplorer = jobExplorer;
        this.jobOperator = jobOperator;
    }


    @RequestMapping(value="/start")
    @ResponseBody
    public String startBatchJobs() throws JobParametersInvalidException
            , JobExecutionAlreadyRunningException
            , JobRestartException
            , JobInstanceAlreadyCompleteException
    {

        JobExecution jobExecution = null;

        Long jobId;

        Set<JobExecution> jobExecutionsSet = jobExplorer.findRunningJobExecutions("checkMgtJob");

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobExecution = jobLauncher.run(job, jobParameters);


        return "Good";
    }

    @RequestMapping(value="/stop")
    @ResponseBody
    public String stopBatchJobs() throws Exception {
        Set<JobExecution> jobExecutionsSet = jobExplorer.findRunningJobExecutions("checkMgtJob");
        for (JobExecution jobExecution : jobExecutionsSet) {

            if (jobExecution.getStatus() == BatchStatus.STARTED || jobExecution.getStatus() == BatchStatus.STARTING) {
                jobOperator.stop(jobExecution.getId());
                System.out.println( jobExecution.getStatus() + "ID :" + jobExecution.getId());
            }
        }

        return "Cancled";
    }
}
