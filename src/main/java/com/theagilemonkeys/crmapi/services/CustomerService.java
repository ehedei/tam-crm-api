package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.Customer;
import com.theagilemonkeys.crmapi.models.UserEntity;
import com.theagilemonkeys.crmapi.repositories.ICustomerRepository;
import java.security.Principal;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found", null));
    }

    @Override
    @Transactional
    public void deleteCustomerById(String id) {
        Customer customer = this.customerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found", null));

        this.customerRepository.delete(customer);

    }

    @Override
    public Customer saveCustomer(Customer customer, Principal principal) {
        customer.setCreatedBy((UserEntity) principal);        
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(String id, Customer newCustomer, Principal principal) {
        UserEntity updatedBy = (UserEntity) principal;
        
        Customer oldCustomer = this.customerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found", null));

        newCustomer.setId(oldCustomer.getId());
        newCustomer.setCreatedBy(oldCustomer.getCreatedBy());
        newCustomer.setUpdatedBy(updatedBy);
        
        return this.customerRepository.save(newCustomer);
    }

}
