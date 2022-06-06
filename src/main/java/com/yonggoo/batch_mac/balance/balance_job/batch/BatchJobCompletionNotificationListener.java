package com.yonggoo.batch_mac.balance.balance_job.batch;

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
public class BatchJobCompletionNotificationListener extends JobExecutionListenerSupport {


    private final JdbcTemplate jdbcTemplate;

    public BatchJobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {

            log.info("!!!Batch JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT firstName, lastName FROM monkey.people3",
                    (rs, row) -> new Person(
                            rs.getString(1),
                            rs.getString(2))
            ).forEach(person -> log.info("Found <" + person + "> in the database."));



        }
    }
}
