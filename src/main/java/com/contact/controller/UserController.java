package com.contact.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.contact.constants.ConstantMessage;
import com.contact.constants.StatusCode;
import com.contact.dto.UserDto;
import com.contact.dto.UserRegisterDto;
import com.contact.http.response.DataResponse;
import com.contact.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public DataResponse login(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {

		try {
			if (bindingResult.hasErrors()) {

				List<String> errorMessage = new ArrayList<String>();
				for (Object object : bindingResult.getAllErrors()) {
					if (object instanceof FieldError) {
						FieldError fieldError = (FieldError) object;
						errorMessage.add(fieldError.getDefaultMessage());
					}
				}

				return new DataResponse(StatusCode.BAD_REQUEST, ConstantMessage.BAD_REQUEST, errorMessage);
			}

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.LOGIN_SUCCESS + ConstantMessage.LOGIN_MESSAGE,
					userService.loginUser(userDto));

		} catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	@PostMapping("/register")
	public DataResponse register(@Valid @RequestBody UserRegisterDto userRegisterDto, BindingResult bindingResult) {

		try {
			if (bindingResult.hasErrors()) {

				List<String> errorMessage = new ArrayList<String>();
				for (Object object : bindingResult.getAllErrors()) {
					if (object instanceof FieldError) {
						FieldError fieldError = (FieldError) object;
						errorMessage.add(fieldError.getDefaultMessage());
					}
				}

				return new DataResponse(StatusCode.BAD_REQUEST, ConstantMessage.BAD_REQUEST, errorMessage);
			}

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK,
					ConstantMessage.USER_ID + userService.registerUser(userRegisterDto));

		} catch (Exception e) {

			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());

		}
	}

}
