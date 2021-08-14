package com.theagilemonkeys.crmapi.repositories;

import com.theagilemonkeys.crmapi.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICustomerRepository extends MongoRepository<Customer, String> {
    
}
