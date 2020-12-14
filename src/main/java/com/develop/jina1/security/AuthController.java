package com.develop.jina1.security;

import com.develop.jina1.user.UserService;
import com.develop.jina1.user.dto.UserCreationCommand;
import com.develop.jina1.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginModel authModel) {
        return new ResponseEntity<>(userService.authenticateUser(authModel), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserCreationCommand userCreationCommand) {
        return new ResponseEntity<>(userService.registerUser(userCreationCommand), HttpStatus.CREATED);
    }
}
