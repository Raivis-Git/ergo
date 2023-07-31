package com.task.ergo.converter;

import com.task.ergo.model.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender category) {
        if (category == null) {
            return null;
        }
        return String.valueOf(category.getValue());
    }

    @Override
    public Gender convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(Gender.values())
                .filter(gender -> gender.getValue().equals(Integer.valueOf(value)))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
