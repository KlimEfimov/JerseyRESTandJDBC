package org.klimefimov.restservice.mappers.entity;

public interface EntityToDTOMapper<T, U> {

    U toDTO(T t);


}
