package com.example.bookstoreapi.model;

import com.example.bookstoreapi.dictionary.Role;
import com.example.bookstoreapi.model.base.CommonIdentifierField;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "users")
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class User extends CommonIdentifierField {

    @Column(nullable = false, length = 40, unique = true)
    String name;

    @Column(nullable = false, length = 50, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    Role role;
}
