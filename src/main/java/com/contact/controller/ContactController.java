package com.contact.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.contact.constants.ConstantMessage;
import com.contact.constants.StatusCode;
import com.contact.dto.ContactDto;
import com.contact.http.response.DataResponse;
import com.contact.model.Contact;
import com.contact.service.ContactService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	ContactService contactService;

	@PostMapping("/create")
	public DataResponse createContact(@Valid @RequestBody ContactDto contactDto, BindingResult bindingResult) {

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

			long id = contactService.createContact(contactDto);

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.CONTACT_CREATED,
					ConstantMessage.CONTACT_ID + id);

		} catch (Exception e) {

			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}

	}

	@GetMapping("/search")
	public DataResponse searchContact(@RequestParam String searchKey) {

		try {

			List<Contact> contacts = contactService.searchContact(searchKey);
			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.CONTACT_FOUND, contacts);
		} catch (Exception e) {

			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	@DeleteMapping("/delete")
	public DataResponse deleteContact(@RequestParam String contact) {
		try {
			long id = contactService.deleteContact(contact);

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.CONTACT_DELETED,
					ConstantMessage.CONTACT_ID + id);
		} catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	@PutMapping("/update")
	public DataResponse updateContact(@Valid @RequestBody ContactDto contactDto, BindingResult bindingResult) {

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
			long id = contactService.updateContact(contactDto);
			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.CONTACT_UPDATED,
					ConstantMessage.CONTACT_ID + id);
		} catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

}
