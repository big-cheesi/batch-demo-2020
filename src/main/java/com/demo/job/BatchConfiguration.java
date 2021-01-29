package com.demo.job;


import com.demo.JobCompletionNotificationListener;
import com.demo.job.data.InviteDto;
import com.demo.job.data.UserDto;
import com.demo.job.json.RegistrationJsonDetails;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BatchConfiguration {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public UserRegistrationWriter userRegistrationWriter() {
    return new UserRegistrationWriter();
  }


  @Bean
  public InviteWriter inviteWriter() {
    return new InviteWriter();
  }

  @Bean
  public UserRegistrationProcessor userRegistrationProcessor() {
    return new UserRegistrationProcessor();
  }

  @Bean
  public InviteProcessor inviteProcessor() {
    return new InviteProcessor();
  }

  @Bean
  public JsonItemReader<RegistrationJsonDetails> userRegistrationReader() {

      return new JsonItemReaderBuilder<RegistrationJsonDetails>()
          .jsonObjectReader(new JacksonJsonObjectReader<>(RegistrationJsonDetails.class))
          .resource(new ClassPathResource("batchdemo.json"))
          .name("registrationDetailsReader")
          .strict(true)
          .build();
  }

  @Bean
  public Step userRegistrationStep1() throws Exception {
    return stepBuilderFactory.get("userRegistrationStep1")
        .<RegistrationJsonDetails, UserDto>chunk(200)
        .reader(userRegistrationReader())
        .processor(userRegistrationProcessor())
        .writer(userRegistrationWriter())
        .throttleLimit(10)
        .build();
  }

  @Bean
  public Step userRegistrationStep2() throws Exception {
    return stepBuilderFactory.get("userRegistrationStep2")
        .<RegistrationJsonDetails, List<InviteDto>>chunk(200)
        .reader(userRegistrationReader())
        .processor(inviteProcessor())
        .writer(inviteWriter())
        .throttleLimit(20)
        .build();
  }

  @Bean
  public Job userRegistrationJob(JobCompletionNotificationListener listener) throws Exception {
    return jobBuilderFactory.get("userRegistrationJob")
        .listener(new JobCompletionNotificationListener())
        .start(userRegistrationStep1())
        .next(userRegistrationStep2())
        .incrementer(new RunIdIncrementer())
        .build();
  }
}
