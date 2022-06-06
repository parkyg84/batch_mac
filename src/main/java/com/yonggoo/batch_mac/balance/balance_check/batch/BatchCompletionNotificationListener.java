package com.yonggoo.batch_mac.balance.balance_check.batch;


import com.yonggoo.batch_mac.balance.balance_check.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BatchCompletionNotificationListener extends JobExecutionListenerSupport {

 private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BatchCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
           log.info("!!! JOB FINISHED! Time to verify the results");
        }
    }
}

