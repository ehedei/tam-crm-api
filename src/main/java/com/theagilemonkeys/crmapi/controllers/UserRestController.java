
package com.theagilemonkeys.crmapi.controllers;

import com.theagilemonkeys.crmapi.models.UserEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.theagilemonkeys.crmapi.services.IUserEntityService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api")
public class UserRestController {
    
    @Autowired
    private IUserEntityService userService;
    
    @GetMapping("/user")
    public List<UserEntity> getUsers(@RequestParam(defaultValue = "0") Integer page, 
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "username") String sortBy) {
        
        return this.userService.getUsers(page, pageSize, sortBy);
    }
    
    @GetMapping("/user/{id}")
    public UserEntity getUserById(@PathVariable String id) {
        return this.userService.getUserById(id);
    }
    
    @PostMapping("/user")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return this.userService.createUser(user);
    }
    
    @PutMapping("/user/{id}")
    public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity user) {
        return this.userService.updateUserById(id, user);
    }
    
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userService.deleteUserById(id);
    }
}
