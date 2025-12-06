package com.innowise.task4.mapper;

public interface DtoMapper<F, T> {
    T map(F from);
}
