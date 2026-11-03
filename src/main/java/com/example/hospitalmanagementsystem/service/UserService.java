package com.example.hospitalmanagementsystem.service;
import com.example.hospitalmanagementsystem.model.User;
import com.example.hospitalmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User register(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
    public User findByUsername(String username)
    {
        return repository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }
    public  User Login(String username , String password) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid Username"));

if (!passwordEncoder.matches(password , user.getPassword()))
{
    throw new RuntimeException("Invalid Password");
}
return user;
    }
    public String resetPassword(String username, String newPassword) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);

        return "Password reset successfully";
    }
}
