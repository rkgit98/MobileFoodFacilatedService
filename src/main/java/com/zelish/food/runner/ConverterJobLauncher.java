package com.zelish.food.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConverterJobLauncher implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;

	@Override
	public void run(String... args) throws Exception {
		jobLauncher.run(job, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
	}
}
