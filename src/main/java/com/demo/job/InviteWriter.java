package com.demo.job;

import com.demo.job.data.InviteDto;
import com.demo.job.data.InviteRepo;
import com.demo.job.data.UserDto;
import com.demo.job.data.UserRepo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class InviteWriter implements ItemWriter<List<InviteDto>> {
  @Autowired
  private InviteRepo inviteRepo;

  @Override
  public void write(List<? extends List<InviteDto>> invitesToSave) throws Exception {
    List<InviteDto> invites = new ArrayList<>();

    for (List<InviteDto> inviteSet: invitesToSave) {
      if(!inviteSet.isEmpty()) {
        invites.addAll(inviteSet);
      }
    }

    inviteRepo.saveAll(invites);
  }
}
