package com.example.hospitalmanagementsystem.controller;
import com.example.hospitalmanagementsystem.model.User;
import com.example.hospitalmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.hospitalmanagementsystem.security.JwtUtil;
import com.example.hospitalmanagementsystem.dto.LoginRequest;
import com.example.hospitalmanagementsystem.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class UserController
{
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        return  service.register(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request)
    {
        User user = service.Login(request.getUsername() , request.getPassword());

        String token = jwtUtil.generateToken(user.getUsername());

        return  new LoginResponse(token);
    }
}
