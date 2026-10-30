package com.blueThink.SQLi.service;

import com.blueThink.SQLi.dto.UserDTO;
import com.blueThink.SQLi.entity.User;

public interface UserService {
    User registerUser(User user);

    UserDTO loginUser(String userName, String password);
}
