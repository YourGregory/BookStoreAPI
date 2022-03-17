package com.example.bookstoreapi.dto.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {

    @NotBlank
    @Size(min = 4, max = 15)
    String name;

    @NotBlank
    @Size(max = 100)
    @Email
    String email;

    @NotBlank
    @Size(min = 4, max = 30)
    String password;
}
