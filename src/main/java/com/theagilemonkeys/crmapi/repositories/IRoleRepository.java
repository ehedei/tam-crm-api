
package com.theagilemonkeys.crmapi.repositories;

import com.theagilemonkeys.crmapi.models.ERole;
import com.theagilemonkeys.crmapi.models.Role;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRoleRepository  extends MongoRepository<Role, String>{
    public Optional<Role> findByName(ERole name);
}
