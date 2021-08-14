package com.theagilemonkeys.crmapi.controllers;

import com.theagilemonkeys.crmapi.models.Customer;
import com.theagilemonkeys.crmapi.services.ICustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CustomerRestController {
    
    @Autowired
    private ICustomerService customerService;
    
    @GetMapping("/customer")
    public List<Customer> getCustomers() {
        return this.customerService.getCustomers();
    }
    
}
