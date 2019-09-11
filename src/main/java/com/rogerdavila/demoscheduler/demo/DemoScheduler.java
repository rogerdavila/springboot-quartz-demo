package com.rogerdavila.demoscheduler.demo;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import javax.annotation.PostConstruct;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoScheduler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String GROUP_NAME = "DemoGroup";

	@PostConstruct
	public void init() {
		logger.info("Hellow world from Spring W/Quartz");
	}

	@Bean("job")
	public JobDetail jobDetail() {
		JobBuilder jobBuilder = JobBuilder.newJob(DemoJob.class)
				.storeDurably()
				.withIdentity("demo_job", GROUP_NAME)
				.withDescription("Invoke of DemoJob");
		
		return jobBuilder.build();
	}
	
	@Bean
	public Trigger trigger(JobDetail job) {
		return TriggerBuilder.newTrigger()
				.forJob(job)
				.withIdentity("Demo_Trigger", GROUP_NAME)
				.withDescription("Demo Trigger")
				.withSchedule(
						simpleSchedule()
						.repeatForever()
						.withIntervalInMilliseconds(DemoService.EXECUTION_TIME)
				)
				.usingJobData("flag", false)
				.build();
	}
	
	@Bean
	public Trigger trigger1(JobDetail job) {
		return TriggerBuilder.newTrigger()
				.forJob(job)
				.withIdentity("Demo_Trigger1", GROUP_NAME)
				.withDescription("Demo Trigger")
				.withSchedule(
						simpleSchedule()
						.repeatForever()
						.withIntervalInMilliseconds(DemoService.EXECUTION_TIME)
				)
				.usingJobData("flag", true)
				.build();
	}

}
