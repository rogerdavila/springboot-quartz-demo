package com.rogerdavila.demoscheduler.demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

	public static final long EXECUTION_TIME = 5000L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AtomicInteger count = new AtomicInteger();

	public void executeDemoJob() {
		logger.info("Sample job has finished with count: {}", count.incrementAndGet());
	}
}