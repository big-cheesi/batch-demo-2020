package com.demo.job.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invite")
@Getter
@Setter
@NoArgsConstructor
public class InviteDto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "invitation_id", nullable = false)
  private int id;

  private String email;
  private String firstName;
  private String lastName;

  @ManyToOne
  @JoinColumn(name="person_id", nullable=false)
  private UserDto referer;

  public InviteDto(String email, String firstName, String lastName, UserDto userDto) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.referer = userDto;
  }
}
