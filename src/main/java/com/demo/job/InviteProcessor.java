package com.demo.job;


import com.demo.job.data.InviteDto;
import com.demo.job.data.UserDto;
import com.demo.job.data.UserRepo;
import com.demo.job.json.FriendJsonDetails;
import com.demo.job.json.RegistrationJsonDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class InviteProcessor implements
    ItemProcessor<RegistrationJsonDetails, List<InviteDto>> {

  @Autowired
  UserRepo userRepo;

  @Override
  public List<InviteDto> process(RegistrationJsonDetails user) throws Exception {

    List<InviteDto> invites = new ArrayList<>();

    return getInvites(user, invites);
  }

  private List<InviteDto> getInvites(RegistrationJsonDetails user, List<InviteDto> invites) {
    List<String> bestFilms = Arrays.asList(new String[] {"Shrek", "Shrek 2"});

    List<String>  greatFilms = Arrays.asList(new String[] {"The Prince of Egypt", "Chicken Run",
        "Wallace & Gromit: The Curse of the Were-Rabbit", "Kung Fu Panda", "How to Train Your Dragon"});

    List<String>  goodFilms = Arrays.asList(new String[] {"Antz", "The Road to El Dorado", "Spirit: Stallion of the Cimarron",
        "Sinbad: Legend of the Seven Seas", "Madagascar", "Puss in Boots", "Rise of the Guardians", "Trolls"});

    List<String>  badFilms = Arrays.asList(new String[] {"Over the Hedge", "Flushed Away"});

    List<String>  genuinelyAwfulFilms = Arrays.asList(new String[] {"Shark Tale", "Bee Movie", "The Boss Baby", "Shrek the Third", "Shrek Forever After"});

    UserDto userDto = new UserDto();
    boolean userBanned = false;
    boolean trollUser = false;

    //user has great taste, let them invite all their friends
    if(bestFilms.contains(user.getFavouriteDreamworksFilm())) {
      for(FriendJsonDetails friend: user.getFriends()) {
        //  public InviteDto(String email, String firstName, String lastName, String inviterEmail) {
        InviteDto inviteDto = new InviteDto(friend.getEmail(), friend.getFirstName(), friend.getLastName(), userDto);
        invites.add(inviteDto);
      }
    }

    //user has decent taste, let them invite 2 of their friends
    else if(greatFilms.contains(user.getFavouriteDreamworksFilm())) {
      for(int i= 0; i<2; i++) {
        FriendJsonDetails friend = user.getFriends().get(i);
        InviteDto inviteDto = new InviteDto(friend.getEmail(), friend.getFirstName(), friend.getLastName(), userDto);
        invites.add(inviteDto);
      }
    }

    //user has ok taste, let them invite 1 of their friends
    else if(goodFilms.contains(user.getFavouriteDreamworksFilm())) {
      FriendJsonDetails friend = user.getFriends().get(0);
      InviteDto inviteDto = new InviteDto(friend.getEmail(), friend.getFirstName(), friend.getLastName(), userDto);
      invites.add(inviteDto);
    }

    //user has bad taste, but may get better in time, do not register and do not ban. Let them try again another time
    //do not save in db. Also don't send invites.
    else if(badFilms.contains(user.getFavouriteDreamworksFilm())) {
      return null;
    }

    userDto = userRepo.findFirstByEmail(user.getEmail());
    for(InviteDto inviteDto :invites) {
      inviteDto.setReferer(userDto);
    }
    return invites;
  }
}
