package com.example.bookstoreapi.mapper;

import com.example.bookstoreapi.dto.security.SignUpRequest;
import com.example.bookstoreapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SignUpRequestToUserMapper {

    User toUser(SignUpRequest signUpRequest);
}
