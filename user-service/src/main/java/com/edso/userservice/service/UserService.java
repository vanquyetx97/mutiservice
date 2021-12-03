package com.edso.userservice.service;

import com.edso.userservice.model.entity.User;
import com.edso.userservice.model.request.LoginRequest;
import com.edso.userservice.model.request.RegisterRequest;
import com.edso.userservice.model.response.LoginResponse;
import com.edso.userservice.model.response.UserResponse;

import java.util.List;

public interface UserService {
    /**
     * Đăng nhập
     * @param request
     * @return
     * @throws Exception
     */
    LoginResponse login(LoginRequest request) throws Exception;
    UserResponse register(RegisterRequest request);
    List<UserResponse> listUsers();
}
