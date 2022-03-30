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
@Getter
@Setter
public abstract class CommonIdentifierField {
    /**
     * Common id field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
