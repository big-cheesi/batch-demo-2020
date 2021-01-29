package com.demo.job;

import com.demo.job.data.UserDto;
import com.demo.job.data.UserRepo;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRegistrationWriter implements ItemWriter<UserDto> {
  @Autowired
  private UserRepo userRepo;

  @Override
  public void write(List<? extends UserDto> users) throws Exception {
    userRepo.saveAll(users);
  }
}
