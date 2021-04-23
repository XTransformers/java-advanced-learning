package com.xtransformers.chapter10.optional;

import java.util.Optional;

public class Client {

    public String getInsuranceName(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
    }

}
