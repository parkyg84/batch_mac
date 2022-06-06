package com.yonggoo.batch_mac;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class BatchMacApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchMacApplication.class, args);
	}

}
