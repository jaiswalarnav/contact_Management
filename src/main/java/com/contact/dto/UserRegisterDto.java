package com.contact.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDto {

	@NotBlank(message = " Name can not be blank")
	private String name;

	@NotBlank(message = "Password field can not be blank")
	@Size(min = 8, message = "Password size must be atleast 8 characters long !")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)(?=.*[@#$%^&+=]).*[A-Za-z0-9]$", message = "Password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit !!")
	private String password;

}
