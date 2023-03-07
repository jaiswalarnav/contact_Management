package com.contact.service;

import com.contact.dto.UserDto;
import com.contact.dto.UserRegisterDto;

public interface UserService {

	String loginUser(UserDto userDto) throws Exception;

	long registerUser(UserRegisterDto userRegisterDto) throws Exception;
}
