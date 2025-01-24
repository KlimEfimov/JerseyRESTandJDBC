package org.klimefimov.restservice.mappers.entity;

public interface DTOToEntityMapper<T, U> {

    U toEntity(T t);

}
