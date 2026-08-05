package com.AtomicSamurai.LimitedSale.Service;

import com.AtomicSamurai.LimitedSale.Entity.Role;
import com.AtomicSamurai.LimitedSale.Entity.User;
import com.AtomicSamurai.LimitedSale.Repository.UserRepository;
import com.AtomicSamurai.LimitedSale.Security.JwtService;
import com.AtomicSamurai.LimitedSale.dto.LoginRequest;
import com.AtomicSamurai.LimitedSale.dto.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email Already Registered");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(Role.USER));

        userRepository.save(user);
    }

    public String login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid Credentials"));
        if(!passwordEncoder.matches(request.getPassword(),user.getPasswordHash())){
            throw new IllegalArgumentException("Invalid Credentials");
        }

        List<String> roles = user.getRoles().stream().map(Enum::name).toList();
        return jwtService.generateToken(user.getEmail(),roles);
    }

}
