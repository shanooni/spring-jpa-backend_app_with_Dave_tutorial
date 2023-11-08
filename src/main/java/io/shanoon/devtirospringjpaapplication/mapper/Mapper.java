package io.shanoon.devtirospringjpaapplication.mapper;

import org.modelmapper.ModelMapper;

public interface Mapper<A,B> {

    A mapFrom(B b);

    B mapTo(A a);

}
