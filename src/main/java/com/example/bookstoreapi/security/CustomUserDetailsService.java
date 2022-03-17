package com.example.bookstoreapi.security;

import com.example.bookstoreapi.config.exceptions.EmailNotFoundException;
import com.example.bookstoreapi.dto.security.UserPrincipal;
import com.example.bookstoreapi.model.User;
import com.example.bookstoreapi.repository.AuthRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailsService implements UserDetailsService {

    AuthRepository userRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        return UserPrincipal.create(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("User with email " + email + " not found"));
        return UserPrincipal.create(user);
    }
}
