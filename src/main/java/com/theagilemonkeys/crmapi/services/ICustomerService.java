package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.Customer;
import java.util.List;

public interface ICustomerService {
    public List<Customer> getCustomers();
    
    public Customer getCustomer();
    
    public void deleteCustomer();
    
    public void updateCustomer();
    
    public Customer createCustomer();
}
