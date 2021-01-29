package com.demo.job.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FriendJsonDetails implements Serializable {

  private static final long serialVersionUID = -1L;
  private String email;
  private String firstName;
  private String lastName;

  @SuppressWarnings("unchecked")
  @JsonProperty("name")
  private void unpackNested(Map<String,Object> name) {
    this.firstName = (String)name.get("first");
    this.firstName = (String)name.get("last");
  }
}
