package com.theagilemonkeys.crmapi.repositories;

import com.theagilemonkeys.crmapi.models.UserEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserEntityRepository extends MongoRepository<UserEntity, String> {

    public Optional<UserEntity> findByUsername(String username);

}
