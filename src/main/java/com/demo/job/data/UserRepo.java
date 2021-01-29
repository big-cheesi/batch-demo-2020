package com.demo.job.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDto, Integer> {
  public UserDto findFirstByEmail(String email);
}
