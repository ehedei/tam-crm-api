package com.theagilemonkeys.crmapi.repositories;

import com.theagilemonkeys.crmapi.models.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
    public Optional<User> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
