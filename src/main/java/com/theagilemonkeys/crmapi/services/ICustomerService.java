package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.Customer;
import java.util.List;

public interface ICustomerService {
    public List<Customer> getCustomers(Integer page, Integer pageSize, String sortBy);
    
    public Customer getCustomerById(String id);
    
    public boolean deleteCustomerById(String id);
    
    public Customer saveCustomer(Customer customer);
    
    public Customer updateCustomer(Customer customer, String id);

}
