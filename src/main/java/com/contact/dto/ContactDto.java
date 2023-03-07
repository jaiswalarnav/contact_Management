package com.contact.dto;

import java.io.Serializable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactDto implements Serializable {

	private long id;

	@NotBlank(message = "first name can not be empty")
	private String firstName;

	@NotBlank(message = "last name can not be empty")
	private String lastName;

	@Email
	@NotBlank(message = "email Id can not be empty")
	private String email;

	@NotBlank(message = "contact no can not be empty")
	@Pattern(regexp = "^[1-9][0-9]*$", message = "enter valid contact number")
	@Size(min = 10, max = 10, message = "contact number should be of 10 digits")
	private String contactNo;

}
