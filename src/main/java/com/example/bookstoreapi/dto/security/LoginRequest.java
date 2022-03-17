package com.example.bookstoreapi.dto.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank
    @Email
    @Size(min = 4, max = 50)
    String email;

    @NotBlank
    @Size(min = 4, max = 30)
    String password;

}
