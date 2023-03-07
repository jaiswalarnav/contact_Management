package com.contact.service;

import java.util.List;
import com.contact.dto.ContactDto;
import com.contact.model.Contact;

public interface ContactService {

	long createContact(ContactDto contactDto) throws Exception;

	List<Contact> searchContact(String searchKey) throws Exception;

	long deleteContact(String contactNo) throws Exception;

	long updateContact(ContactDto contactDto) throws Exception;
}
