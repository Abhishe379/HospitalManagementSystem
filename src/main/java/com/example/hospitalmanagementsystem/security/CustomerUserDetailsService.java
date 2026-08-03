package com.example.hospitalmanagementsystem.security;

import com.example.hospitalmanagementsystem.model.User;
import com.example.hospitalmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;



@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = repository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
               java.util.List.of(
                       new org.springframework.security.core.authority.SimpleGrantedAuthority(
                               "ROLE_" + user.getRole().name()
                       )
               )
        );
    }
}