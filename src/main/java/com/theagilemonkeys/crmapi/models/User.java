package com.theagilemonkeys.crmapi.models;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class User {
  @Id
  private String id;

  @Indexed(unique = true)
  @NotEmpty(message = "Username is required")
  @Size(min = 4, max = 16, message = "Username must be between 2 and 16 characters long")
  private String username;

  @Indexed(unique = true)
  @NotEmpty(message = "Email is required")
  @Email(message = "Invalid email address")
  private String email;

  @NotEmpty(message = "Password is required")
  private String password;

  @DBRef
  private Set<Role> roles = new HashSet<>();
}
