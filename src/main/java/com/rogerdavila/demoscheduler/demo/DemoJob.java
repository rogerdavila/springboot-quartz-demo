package com.rogerdavila.demoscheduler.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoJob implements Job{

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DemoService demoService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		demoService.executeDemoJob();
	}
}
