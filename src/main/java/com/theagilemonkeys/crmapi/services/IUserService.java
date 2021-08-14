package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.User;
import java.util.List;

public interface IUserService {
    public List<User> getUsers(Integer page, Integer pageSize, String sortBy);
    
    public User getUserById(String id);
    
    public void deleteUserById(String id);
    
    public void updateUserById(String id, User user);
    
    public User createUser(User user);
    
}
