package com.blueThink.SQLi.service.impl;

import com.blueThink.SQLi.customException.IncorrectPassword;
import com.blueThink.SQLi.customException.UserNotFound;
import com.blueThink.SQLi.dto.UserDTO;
import com.blueThink.SQLi.entity.User;
import com.blueThink.SQLi.repository.UserRepo;
import com.blueThink.SQLi.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User registerUser(User user) {
        String encodePassword =
                passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setCreatedAt(LocalDateTime.now());

        return userRepo.save(user);
    }

    @Override
    @Transactional
    public UserDTO loginUser(String userName, String password) {
        User user = userRepo.findByUserName(userName)
                .orElseThrow(()->
                        new UserNotFound("UserName not found for this request"));
        boolean passwordMatches =
                passwordEncoder.matches(
                        password,
                        user.getPassword()
                );
        if (!passwordMatches) {
            throw new IncorrectPassword(
                    "Sorry! you have entered invalid password for this login request"
            );
        }
        return new UserDTO(
                user.getUserName(),
                "Encrypted",
                user.getCreatedAt(),
                user.getUpdatedAt(),
                "Login successful"
        );
    }
}
