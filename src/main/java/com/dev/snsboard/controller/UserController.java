package com.dev.snsboard.controller;

import com.dev.snsboard.model.user.User;
import com.dev.snsboard.model.user.UserSignUpRequestBody;
import com.dev.snsboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> signup(@RequestBody UserSignUpRequestBody userSignRequestBody) {
        var user = userService.signUp(
                userSignRequestBody.username(),
                userSignRequestBody.password()
        );
        return ResponseEntity.ok(user);
    }
}
