package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.Customer;
import java.security.Principal;
import java.util.List;

public interface ICustomerService {
    public List<Customer> getCustomers(Integer page, Integer pageSize, String sortBy);
    
    public Customer getCustomerById(String id);
    
    public void deleteCustomerById(String id);
    
    public Customer saveCustomer(Customer customer, Principal principal);
    
    public Customer updateCustomer(String id, Customer customer, Principal principal);

}
