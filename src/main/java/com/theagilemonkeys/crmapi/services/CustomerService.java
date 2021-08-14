package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.Customer;
import com.theagilemonkeys.crmapi.repositories.ICustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteCustomerById(String id) {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isEmpty()) {
            return false;
        } else {
            this.customerRepository.delete(customer.get());
            return true;
        }        
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }
    
    @Override
    public Customer updateCustomer(Customer newCustomer, String id) {
        Customer oldCustomer = this.customerRepository.findById(id).orElse(null);
        
        if (oldCustomer != null) {
            newCustomer.setId(oldCustomer.getId());
            newCustomer.setCreatedBy(oldCustomer.getCreatedBy());
            oldCustomer = this.customerRepository.save(newCustomer);
        }
        
        return oldCustomer;
    }
    
    
}