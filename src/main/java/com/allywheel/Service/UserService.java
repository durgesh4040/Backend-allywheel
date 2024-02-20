package com.allywheel.Service;

import java.util.List;
import java.util.Optional;

import com.allywheel.Model.*;

public interface UserService {

    List<UserModel> getUsers();

    Optional<UserModel> getUserByUsername(String username);

    Optional<UserModel> getUserByEmail(String email);

    boolean hasUserWithUsername(String username);

    boolean hasUserWithEmail(String email);

    UserModel validateAndGetUserByUsername(String username);

    UserModel saveUser(UserModel user);

    void deleteUser(UserModel user);
}
