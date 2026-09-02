package com.AtomicSamurai.LimitedSale.Service;

import com.AtomicSamurai.LimitedSale.Entity.Role;
import com.AtomicSamurai.LimitedSale.Entity.User;
import com.AtomicSamurai.LimitedSale.Repository.UserRepository;
import com.AtomicSamurai.LimitedSale.Security.JwtService;
import com.AtomicSamurai.LimitedSale.dto.LoginRequest;
import com.AtomicSamurai.LimitedSale.dto.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

        // create an authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());

        // authenticate the user using authentication manager
        Authentication authenticated = authenticationManager.authenticate(authentication);

        User user = (User) authenticated.getPrincipal();

        List<String> roles = user.getRoles().stream().map(Enum::name).toList();
        return jwtService.generateToken(user.getEmail(),roles);
    }

}
