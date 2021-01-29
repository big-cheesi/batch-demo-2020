package com.demo.job.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "person_id", nullable = false)
  private int id;

  private String email;
  private String firstName;
  private String lastName;

  private String favouriteDreamworksFilm;

  private String company;
  private String phoneNumber;

  private boolean banned;

  private Date dateCreated;
  private Date dateUpdated;

  private boolean trollUser;

  private int age;

  @OneToMany(mappedBy="referer")
  @JsonIgnore
  private Set<InviteDto> invites = new HashSet<>();

  public UserDto(String email, String firstName, String lastName,
      String favouriteDreamworksFilm, String company,
      String phoneNumber, boolean banned, boolean trollUser, int age) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.favouriteDreamworksFilm = favouriteDreamworksFilm;
    this.company = company;
    this.phoneNumber = phoneNumber;
    this.banned = banned;
    this.trollUser = trollUser;
    this.age = age;

    Date now = Calendar.getInstance().getTime();
    this.setDateCreated(now);
    this.setDateUpdated(now);

  }
}
