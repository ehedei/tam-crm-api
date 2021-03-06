package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.Customer;
import com.theagilemonkeys.crmapi.models.UserEntity;
import com.theagilemonkeys.crmapi.repositories.ICustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers(Integer page, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return this.customerRepository.findAll(pageable).getContent();
    }

    @Override
    public Customer getCustomerById(String id) {
        return this.customerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @Override
    @Transactional
    public void deleteCustomerById(String id) {
        Customer customer = this.customerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        this.customerRepository.delete(customer);

    }

    @Override
    public Customer saveCustomer(Customer customer, UserEntity user) {
        customer.setCreatedBy(user);        
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(String id, Customer newCustomer, UserEntity user) {
        Customer oldCustomer = this.customerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        newCustomer.setId(oldCustomer.getId());
        newCustomer.setCreatedBy(oldCustomer.getCreatedBy());
        newCustomer.setUpdatedBy(user);
        
        return this.customerRepository.save(newCustomer);
    }

}
