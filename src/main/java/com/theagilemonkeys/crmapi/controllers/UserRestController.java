
package com.theagilemonkeys.crmapi.controllers;

import com.theagilemonkeys.crmapi.models.User;
import com.theagilemonkeys.crmapi.services.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserRestController {
    
    @Autowired
    private IUserService userService;
    
    @GetMapping("/user")
    public List<User> getUsers(@RequestParam(defaultValue = "0") Integer page, 
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "username") String sortBy) {
        
        return this.userService.getUsers(page, pageSize, sortBy);
    }
}
