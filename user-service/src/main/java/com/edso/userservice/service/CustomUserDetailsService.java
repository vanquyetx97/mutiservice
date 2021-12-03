package com.edso.userservice.service;

import com.edso.userservice.model.request.RegisterRequest;
import com.edso.userservice.model.response.UserResponse;
import com.edso.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles;

        com.edso.userservice.model.entity.User user = userRepository.findByUsername(username);
        if (user != null) {
            roles = List.of(new SimpleGrantedAuthority(user.getRole()));
            return new User(user.getUsername(), user.getPassword(), roles);
        }
        throw new UsernameNotFoundException("User not found with the username " + username);
    }

    public UserResponse save(RegisterRequest user) {
        UserResponse response = new UserResponse();
        com.edso.userservice.model.entity.User userSave = new com.edso.userservice.model.entity.User();
        userSave.setUsername(user.getUsername());
        userSave.setPassword(bcryptEncoder.encode(user.getPassword()));
        userSave.setRole("USER");
        userRepository.save(userSave);
        response.setUsername(userSave.getUsername());
        return response;
    }
}
