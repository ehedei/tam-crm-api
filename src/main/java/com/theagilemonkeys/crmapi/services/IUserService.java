package com.theagilemonkeys.crmapi.services;

import com.theagilemonkeys.crmapi.models.User;
import java.util.List;

public interface IUserService {
    public List<User> getUsers(Integer page, Integer pageSize, String sortBy);
    
    public User getUser();
    
    public void deleteUser();
    
    public void updateUser();
    
    public User createUser();
    
}
