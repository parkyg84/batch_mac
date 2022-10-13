package com.yonggoo.batch_mac.account.buyer_receipt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class BuyerReceiptCompletionNotificationListener implements JobExecutionListener {
    Logger logger = LoggerFactory.getLogger(BuyerReceiptCompletionNotificationListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Called beforeJob().");
    }
    

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("Called afterJob().");
        logger.info(jobExecution.getFailureExceptions().toString());
        logger.info(jobExecution.getStepExecutions().toString());
    }
}
