package com.example.bookstoreapi.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Common 'id' part of all entities.
 */
@MappedSuperclass
@ToString
public abstract class CommonIdentifierField {
    /**
     * Common id field.
     */
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
