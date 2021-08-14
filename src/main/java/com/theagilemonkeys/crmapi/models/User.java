package com.theagilemonkeys.crmapi.models;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class User {
    @Id
  private String id;

  private String username;


  private String email;

  private String password;

  @DBRef
  private Set<Role> roles = new HashSet<>();
}
