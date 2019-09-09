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

	@PostConstruct
	public void init() {
		logger.info("Hellow world from Spring W/Quartz");
	}

	@Bean("job")
	public JobDetail jobDetail() {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("flag", false);
		
		JobBuilder jobBuilder = JobBuilder.newJob()
				.ofType(DemoJob.class)
				.storeDurably()
				.withIdentity("demo_job", "DemoGroup")
				.withDescription("Invoke of DemoJob")
				.setJobData(jobDataMap);
		
		return jobBuilder.build();
	}

	@Bean("job1")
	public JobDetail jobDetail1() {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("flag", true);
		
		JobBuilder jobBuilder = JobBuilder.newJob()
				.ofType(DemoJob.class)
				.storeDurably()
				.withIdentity("demo_job1", "DemoGroup")
				.withDescription("Invoke of DemoJob1")
				.setJobData(jobDataMap);
		
		return jobBuilder.build();
	}
	
	@Bean
	public Trigger trigger(@Qualifier("job") JobDetail job) {
		return TriggerBuilder.newTrigger()
				.forJob(job)
				.withIdentity("Demo_Trigger", "DemoGroup")
				.withDescription("Demo Trigger")
				.withSchedule(
						simpleSchedule()
						.repeatForever()
						.withIntervalInMilliseconds(DemoService.EXECUTION_TIME)
				)
				.build();
	}
	
	@Bean
	public Trigger trigger1(@Qualifier("job1") JobDetail job) {
		return TriggerBuilder.newTrigger()
				.forJob(job)
				.withIdentity("Demo_Trigger1", "DemoGroup")
				.withDescription("Demo Trigger")
				.withSchedule(
						simpleSchedule()
						.repeatForever()
						.withIntervalInMilliseconds(DemoService.EXECUTION_TIME)
				)
				.build();
	}

}
