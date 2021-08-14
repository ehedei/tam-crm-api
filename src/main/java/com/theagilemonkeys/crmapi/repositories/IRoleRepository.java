
package com.theagilemonkeys.crmapi.repositories;

import com.theagilemonkeys.crmapi.models.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRoleRepository  extends MongoRepository<Role, String>{

}
