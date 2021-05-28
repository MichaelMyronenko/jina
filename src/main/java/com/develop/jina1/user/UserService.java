package com.develop.jina1.user;

import com.develop.jina1.basket.Basket;
import com.develop.jina1.basket.BasketRepository;
import com.develop.jina1.error.ConflictException;
import com.develop.jina1.error.NotFoundException;
import com.develop.jina1.security.JwtTokenProvider;
import com.develop.jina1.security.TokenDto;
import com.develop.jina1.security.rereshToken.RefreshToken;
import com.develop.jina1.security.rereshToken.RefreshTokenRepository;
import com.develop.jina1.security.userLogin.AuthenticatedUser;
import com.develop.jina1.security.userLogin.LoginModel;
import com.develop.jina1.user.dto.UserCreationCommand;
import com.develop.jina1.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserDto registerUser(UserCreationCommand userCreationCommand) {
        if (userRepository.findByUsername(userCreationCommand.getUsername()).isPresent()) {
            throw new ConflictException("AuthenticatedUser with username " +
                    userCreationCommand.getUsername() + " already exists");
        }

        if (!PasswordMatcher.isValidPassword(userCreationCommand.getPassword())) {
            throw new ConflictException("Invalid password");
        }
        User user = userMapper.dtoToEntity(userCreationCommand);
        user.setPassword(passwordEncoder.encode(userCreationCommand.getPassword()));
        user.setRole(Role.USER);
        user = userRepository.save(user);
        Basket basket = new Basket(user.getId());
        basketRepository.save(basket);
        return userMapper.entityToDto(user);
    }

    public User processUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Not found User with id " + userId));
    }

    public TokenDto authenticateUser(LoginModel authModel) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authModel.getUsername(),
                authModel.getPassword());
        AuthenticatedUser principal = (AuthenticatedUser) authenticationManager
                .authenticate(authentication).getPrincipal();
        String strAccessToken = jwtTokenProvider.createAccessToken(principal);

        String refreshTokenUuId = UUID.randomUUID().toString();
        String strRefreshToken = jwtTokenProvider
                .createRefreshToken(refreshTokenUuId, principal);

        refreshTokenRepository.findByUserId(principal.getId())
                .ifPresent(refreshTokenRepository::delete);

        RefreshToken refreshToken = new RefreshToken(principal.getId(), refreshTokenUuId);
        refreshTokenRepository.save(refreshToken);

        return new TokenDto(strAccessToken, strRefreshToken);
    }
}
