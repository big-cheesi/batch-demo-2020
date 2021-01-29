package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Job completion notification listener.
 * @author Linda McCall
 *
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
  long startTime;

  @Override
  public void beforeJob(JobExecution jobExecution) {
    startTime = System.currentTimeMillis();
    log.info("!!! OH LAWD JOB STARTED!       :) ");
  }
  
  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      long timeTakenSeconds = (System.currentTimeMillis() - startTime) / 1000;
      log.info("!!! JOB FINISHED! Time to verify the results. Job took " + timeTakenSeconds + " seconds to execute");
    }
    else {
      long timeTakenSeconds = (System.currentTimeMillis() - startTime) / 1000;
      log.info("!!! JOB FINISHED WITH " + jobExecution.getStatus() + " STATUS! Time to verify the results. Job took " + timeTakenSeconds + " seconds to execute");
    }
  }
}
