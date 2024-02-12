package com.bookstore.com.authentication;

import com.bookstore.com.authentication.User.LoginReq;
import com.bookstore.com.authentication.User.LoginResponse;
import com.bookstore.com.authentication.User.RegisterReq;
import com.bookstore.com.authentication.User.RegisterResponse;
import com.bookstore.com.authentication.userDetailService.UserService;
import com.bookstore.com.exception.NullObjectRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authentication")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterReq registerReq) {
        if (registerReq == null) {
            throw new NullObjectRequest("register request is null");
        }
        return ResponseEntity.ok(userService.register(registerReq));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginReq loginReq){
        if (loginReq == null){
            throw new NullObjectRequest("login form is null");
        }

        return ResponseEntity.ok(userService.login(loginReq));
    }





}
