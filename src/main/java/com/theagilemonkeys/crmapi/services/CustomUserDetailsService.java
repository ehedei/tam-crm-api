package com.theagilemonkeys.crmapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userEntityService
                .getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
    
    public UserDetails loadUserById(String id) {
        return this.userEntityService.getUserById(id);
    }
}
