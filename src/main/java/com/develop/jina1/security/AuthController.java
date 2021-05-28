package com.develop.jina1.security;

import com.develop.jina1.security.rereshToken.RefreshTokenCommand;
import com.develop.jina1.security.rereshToken.RefreshTokenService;
import com.develop.jina1.security.userLogin.LoginModel;
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
    private final RefreshTokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginModel authModel) {
        return new ResponseEntity<>(userService.authenticateUser(authModel), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserCreationCommand userCreationCommand) {
        return new ResponseEntity<>(userService.registerUser(userCreationCommand), HttpStatus.CREATED);
    }

    @PostMapping("/refresh_access_token")
    public ResponseEntity<TokenDto> refreshToken(@RequestBody RefreshTokenCommand tokenCommand) {
        return new ResponseEntity<>(tokenService.refreshAccessToken(tokenCommand), HttpStatus.OK);
    }
}
