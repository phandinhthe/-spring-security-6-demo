package com.terry.security.demo.springsecurity6demo.configuration;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.*;

@Converter
public class StringListConverter implements AttributeConverter<Collection<String>, String> {
	@Override
	public String convertToDatabaseColumn(Collection<String> attribute) {
		StringJoiner joiner = new StringJoiner(",");
		Optional.ofNullable(attribute).orElse(Collections.emptyList()).forEach(joiner::add);
		return joiner.toString();
	}

	@Override
	public Collection<String> convertToEntityAttribute(String dbData) {
		return Arrays.stream(Optional.ofNullable(dbData).orElse("").split(",")).toList();
	}
}
