package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.Customer;
import com.theagilemonkeys.crmapi.models.UserEntity;
import java.util.List;

public interface ICustomerService {
    public List<Customer> getCustomers(Integer page, Integer pageSize, String sortBy);
    
    public Customer getCustomerById(String id);
    
    public void deleteCustomerById(String id);
    
    public Customer saveCustomer(Customer customer, UserEntity user);
    
    public Customer updateCustomer(String id, Customer customer, UserEntity user);

}
