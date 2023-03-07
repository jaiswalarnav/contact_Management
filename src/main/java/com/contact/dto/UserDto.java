package com.contact.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

	@NotNull(message = "User ID can not be empty")
	private Integer id;

	@NotBlank(message = "Password can not be empty")
	@Size(min = 8, message = "Password size must be atleast 8 characters long !")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)(?=.*[@#$%^&+=]).*[A-Za-z0-9]$", message = "Password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit !!")
	private String password;

}
