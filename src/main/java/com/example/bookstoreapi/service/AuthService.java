package com.example.bookstoreapi.service;

import com.example.bookstoreapi.config.PropertiesConfig;
import com.example.bookstoreapi.dictionary.Role;
import com.example.bookstoreapi.dto.security.JwtAuthenticationResponse;
import com.example.bookstoreapi.dto.security.LoginRequest;
import com.example.bookstoreapi.dto.security.SignUpRequest;
import com.example.bookstoreapi.dto.security.UserPrincipal;
import com.example.bookstoreapi.mapper.SignUpRequestToUserMapperImpl;
import com.example.bookstoreapi.model.User;
import com.example.bookstoreapi.repository.AuthRepository;
import com.example.bookstoreapi.security.JwtTokenProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthService {

    AuthRepository authRepository;
    SignUpRequestToUserMapperImpl requestToUserMapper;

    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider tokenProvider;
    private final PropertiesConfig.JwtProperties properties;

    @Transactional(readOnly = true)
    public Boolean existsByUsername(String name) {
        return authRepository.existsByName(name);
    }

    @Transactional(readOnly = true)
    public Boolean existsByEmail(String email) {
        return authRepository.existsByEmail(email);
    }

    @Transactional
    public void registerUser(SignUpRequest request) {
        User user = requestToUserMapper.toUser(request);
        user.setRole(Role.CLIENT);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        System.out.println(user.getEmail());
        authRepository.save(user);
    }

    @Transactional
    public JwtAuthenticationResponse authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("here1");
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        String accessToken = createAccessToken(authentication);
        String refreshToken = createRefreshToken(authentication);
        System.out.println(accessToken);
        System.out.println(refreshToken);
        System.out.println("here1");
        return new JwtAuthenticationResponse(accessToken, refreshToken, userId);
    }

    public String createAccessToken(Authentication authentication) {
        return tokenProvider.generateToken(authentication, properties.getExpirationAccessToken());
    }

    public String createRefreshToken(Authentication authentication) {
        return tokenProvider.generateToken(authentication, properties.getExpirationRefreshToken());
    }
}
