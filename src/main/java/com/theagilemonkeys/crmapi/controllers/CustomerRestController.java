package com.theagilemonkeys.crmapi.controllers;

import com.theagilemonkeys.crmapi.models.Customer;
import com.theagilemonkeys.crmapi.models.UserEntity;
import com.theagilemonkeys.crmapi.services.ICustomerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CustomerRestController {
    
    @Autowired
    private ICustomerService customerService;
    
    @GetMapping("/customer")
    public List<Customer> getCustomers(@RequestParam(defaultValue = "0") Integer page, 
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "id") String sortBy) {
        
        return this.customerService.getCustomers(page, pageSize, sortBy);
    }
    
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable String id) {
        return this.customerService.getCustomerById(id);
    }
    
    @PostMapping(path="/customer", consumes="application/json")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer, @AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.saveCustomer(customer, user));
    }
    
    @PutMapping(path="/customer/{id}", consumes="application/json")
    public Customer updateCustomer(@Valid @RequestBody Customer customer, @PathVariable String id, @AuthenticationPrincipal UserEntity user) {
        return this.customerService.updateCustomer(id, customer, user);        
    }
    
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable String id) {
        this.customerService.deleteCustomerById(id);
    }
    
}
