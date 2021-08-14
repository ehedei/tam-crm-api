package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.User;
import com.theagilemonkeys.crmapi.repositories.IUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> getUsers(Integer page, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return this.userRepository.findAll(pageable).getContent();
    }

    @Override
    public User getUserById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUserById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUserById(String id, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User createUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
