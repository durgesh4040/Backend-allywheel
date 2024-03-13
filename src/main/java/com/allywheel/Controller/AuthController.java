package com.allywheel.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allywheel.Exception.DuplicatedUserInfoException;
import com.allywheel.Model.UserModel;
import com.allywheel.Security.TokenProvider;
import com.allywheel.Security.Oauth2.Oauth2Provider;
import com.allywheel.Service.UserService;
import com.allywheel.Security.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.getEmail(), loginRequest.getPassword());
        return  new AuthResponse(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
//        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
//            throw new DuplicatedUserInfoException(String.format("Username %s already been used", signUpRequest.getUsername()));
//        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new DuplicatedUserInfoException(String.format("Email %s already been used", signUpRequest.getEmail()));
        }
            System.out.println("hi");
        userService.saveUser(mapSignUpRequestToUser(signUpRequest));

        String token = authenticateAndGetToken(signUpRequest.getEmail(), signUpRequest.getPassword());
        System.out.println(token);
        return  new AuthResponse(token);
    }

    private String authenticateAndGetToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return tokenProvider.generate(authentication);
    }

    private UserModel mapSignUpRequestToUser(SignUpRequest signUpRequest) {
        UserModel user = new UserModel();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(UserRole.ROLE_USER);
        user.setProvider(Oauth2Provider.LOCAL);
        return user;
    }
}
