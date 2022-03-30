package com.example.bookstoreapi.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum Role {

    CLIENT("client"),
    ADMIN("admin");

    static final Map<String, Role> BY_NAME = new HashMap<>();

    static {
        for (Role element : values()) {
            BY_NAME.put(element.value, element);
        }
    }

    String value;

    public static Role valueOfName(String value) {
        return BY_NAME.get(value);
    }
}
