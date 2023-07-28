package com.dans.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dans.api.dto.AuthRequest;
import com.dans.api.dto.LoginResponse;
import com.dans.api.dto.ResponseModel;
import com.dans.api.models.entities.Users;
import com.dans.api.repository.UserRepository;
import com.dans.api.utils.jwt.JwtHelper;

@Service
public class AuthService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public JwtHelper jwtHelper;

    public ResponseModel login(AuthRequest request) throws Exception {
        ResponseModel response = new ResponseModel();

        userRepository.findByUsernameAndPassword(request.username, request.password).orElseThrow();

        LoginResponse resp = new LoginResponse();
        resp.setMessage("Login successfully");
        resp.setToken(jwtHelper.generateToken(request.username));

        response.data = resp;
        response.status = 200;

        return response;
    }

    public ResponseModel register(AuthRequest request) throws Exception {
        ResponseModel response = new ResponseModel();
        Users user = userRepository.findByUsername(request.username).orElse(new Users());

        if (user.getUsername() != null) {
            throw new Exception("Username already registered");
        }

        user.setUsername(request.username);
        user.setPassword(request.password);

        userRepository.save(user);

        response.status = 200;
        response.data = "User registered succesfully";

        return response;
    }
}
