package com.edso.userservice.service;

import com.edso.userservice.helper.CheckValid;
import com.edso.userservice.model.entity.User;
import com.edso.userservice.model.request.LoginRequest;
import com.edso.userservice.model.request.RegisterRequest;
import com.edso.userservice.model.response.LoginResponse;
import com.edso.userservice.model.response.UserResponse;
import com.edso.userservice.repository.UserRepository;
import com.edso.userservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate template;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Đăng nhập
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public LoginResponse login(LoginRequest request) throws Exception {
        CheckValid.checkLoginRequest(request, userRepository);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userdetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userdetails);
        User user = userRepository.findByUsername(request.getUsername());
        String username = user.getUsername();

        template.opsForValue().set(token, username);
        System.out.println("Value of key token: "+template.opsForValue().get(token));

        return new LoginResponse(username, token);
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        CheckValid.checkRegisterRequest(request, userRepository);
        return userDetailsService.save(request);
    }

    @Override
    public List<UserResponse> listUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        for (User u : users) {
            UserResponse r = new UserResponse();
            r.setUsername(u.getUsername());
            responses.add(r);
        }
        return responses;
    }
}
