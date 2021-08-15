package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.UserEntity;
import java.util.List;
import java.util.Optional;

public interface IUserEntityService {
    public List<UserEntity> getUsers(Integer page, Integer pageSize, String sortBy);
    
    public Optional<UserEntity> getUserByUsername(String username);
    
    public UserEntity getUserById(String id);
    
    public void deleteUserById(String id);
    
    public void updateUserById(String id, UserEntity user);
    
    public UserEntity createUser(UserEntity user);
}
