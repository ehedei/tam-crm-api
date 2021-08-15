package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.UserEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.theagilemonkeys.crmapi.repositories.IUserEntityRepository;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserEntltyService implements IUserEntityService {
    
    @Autowired
    private IUserEntityRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getUsers(Integer page, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return this.userRepository.findAll(pageable).getContent();
    }

    @Override
    public UserEntity getUserById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUserById(String id, UserEntity user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));       
        user = this.userRepository.save(user);
        return user;
    }

    @Override
    public Optional<UserEntity> getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
