package com.rogerdavila.demoscheduler.demo;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import javax.annotation.PostConstruct;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class DemoScheduler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void init() {
		logger.info("Hellow world from Spring W/Quartz");
	}

	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob()
				.ofType(DemoJob.class)
				.storeDurably()
				.withIdentity("demojob")
				.withDescription("Invoke of DemoJob")
				.build();
	}

	@Bean
	public Trigger trigger(JobDetail job) {
		return TriggerBuilder.newTrigger()
				.forJob(job)
				.withIdentity("Demo_Trigger")
				.withDescription("Demo Trigger")
				.withSchedule(simpleSchedule().repeatForever().withIntervalInMilliseconds(DemoService.EXECUTION_TIME))
				.build();
	}
	
	@Bean
	public Scheduler scheduler(SchedulerFactoryBean factory, Trigger trigger, JobDetail job) throws SchedulerException {
		
		Scheduler scheduler = factory.getScheduler();
		
		if(!scheduler.checkExists(job.getKey())) { 
			scheduler.scheduleJob(job, trigger);
		}
		
		scheduler.start();
		return scheduler;
	}
}
