package com.diagonline.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.diagonline.utils.UserType;

@Component
public class UserTypeToStringConverter implements Converter<UserType, String>{

	@Override
	public String convert(UserType source) {
		return source.name();
	}

}
