package com.demo.job.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationJsonDetails implements Serializable {

  private static final long serialVersionUID = -1L;

  @JsonProperty("guid")
  private String id;

  private String email;
  private String firstName;
  private String lastName;

  @JsonProperty("favoriteDreamworksFilm")
  private String favouriteDreamworksFilm;

  private String about;
  private String company;

  @JsonProperty("friends")
  private List<FriendJsonDetails> friends;

  @JsonProperty("phone")
  private String phoneNumber;

  private int age;

  @SuppressWarnings("unchecked")
  @JsonProperty("name")
  private void unpackNested(Map<String,Object> name) {
    this.firstName = (String)name.get("first");
    this.firstName = (String)name.get("last");
  }


}
