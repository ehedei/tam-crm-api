package com.theagilemonkeys.crmapi.controllers;

import com.theagilemonkeys.crmapi.models.Customer;
import com.theagilemonkeys.crmapi.services.ICustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api")
public class CustomerRestController {
    
    @Autowired
    private ICustomerService customerService;
    
    @GetMapping("/customer")
    public List<Customer> getCustomers() {
        return this.customerService.getCustomers();
    }
    
    @GetMapping("/customer/{id}")
    public Customer getCustomers(@PathVariable String id) {
        Customer customer = this.customerService.getCustomerById(id);
        
        if(customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found", null);
        } else {
            return customer;
        }
    }
    
    @PostMapping(path="/customer", consumes="application/json")
    public Customer saveCustomer(@RequestBody Customer customer) {
        // customer.setCreatedBy(user); TODO Get User through Authorization and add to customer (createdBy)
        customer = this.customerService.saveCustomer(customer);
        return customer;
    }
    
    @PutMapping(path="/customer/{id}", consumes="application/json")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable String id) {
        // customer.setUpdatedBy(user); TODO Get User through Authorization and add to customer (updatedBy)
        customer = this.customerService.updateCustomer(customer, id);
        
        if(customer != null) {
            return customer;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found", null);
        }
        
    }
    
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable String id) {
        if(!this.customerService.deleteCustomerById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found", null);
        }
    }
    
}
