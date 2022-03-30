package com.example.bookstoreapi.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse {

    @AllArgsConstructor
    @Getter
    enum RESPONSE {

        USERNAME_ALREADY_TAKEN("Username  is already taken!"),
        EMAIL_ALREADY_TAKEN("Email  is already taken!"),
        SUCCESSFUL_REGISTER("User registered successfully!");

        String message;
    }

    Boolean success;
    String message;

    public static ApiResponse createUsernameAlreadyTakenResponse() {
        return new ApiResponse(false, RESPONSE.USERNAME_ALREADY_TAKEN.getMessage());
    }

    public static ApiResponse createEmailAlreadyTakenResponse() {
        return new ApiResponse(false, RESPONSE.EMAIL_ALREADY_TAKEN.getMessage());
    }

    public static ApiResponse createSuccessfulRegisterResponse() {
        return new ApiResponse(true, RESPONSE.SUCCESSFUL_REGISTER.getMessage());
    }
}
