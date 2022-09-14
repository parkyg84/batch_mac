package com.yonggoo.batch_mac;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@EnableAutoConfiguration
@SpringBootApplication
public class BatchMacApplication {

	@Autowired
	private JobRegistry jobRegistry;

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(){
		JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
		postProcessor.setJobRegistry(jobRegistry);
		return postProcessor;
	}



	public static void main(String[] args) {
		SpringApplication.run(BatchMacApplication.class, args);
	}



}


/*
--job.name=simpleJob version=1


$ git rebase -i HEAD~3
$ git log --pretty=oneline

spring.batch.job.names: ${job.name:EMPTY}

https://devfunny.tistory.com/690
 */