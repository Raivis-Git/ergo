package com.task.ergo.model;

import com.task.ergo.exception.PersonException;

import java.util.Arrays;

public enum Gender {
    MALE(1), FEMALE(2);

    private final Integer value;

    Gender(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Gender getGender(Integer value) throws PersonException {
        return Arrays.stream(values())
                .filter(gender -> gender.value.equals(value))
                .findFirst().orElseThrow(() -> new PersonException("No such gender"));
    }

    public static boolean genderExists(Integer value) {
        if (value == null)
            return false;

        return Arrays.stream(values())
                .anyMatch(gender -> gender.value.equals(value));
    }

}
