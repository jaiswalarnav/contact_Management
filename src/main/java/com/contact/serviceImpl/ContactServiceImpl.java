package com.contact.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contact.constants.ConstantMessage;
import com.contact.dto.ContactDto;
import com.contact.model.Contact;
import com.contact.repository.ContactRepository;
import com.contact.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	ModelMapper modelMapper;

	public long createContact(ContactDto contactDto) throws Exception {

		if (contactRepository.findByContactNo(contactDto.getContactNo()) != null)
			throw new RuntimeException(ConstantMessage.EXISTING_CONTACT);

		Contact contact = new Contact();

		modelMapper.map(contactDto, contact);

		return (contactRepository.save(contact).getId());
	}

	public List<Contact> searchContact(String searchKey) throws Exception {

		List<Contact> contacts = contactRepository
				.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(searchKey,
						searchKey, searchKey);

		if (contacts.isEmpty())
			throw new RuntimeException(ConstantMessage.CONTACT_NOT_FOUND);

		return contacts;
	}

	public long deleteContact(String contactNo) throws Exception {

		Contact contact = contactRepository.findByContactNo(contactNo);

		if (contact.equals(null))
			throw new RuntimeException(ConstantMessage.CONTACT_NOT_FOUND);

		contactRepository.delete(contact);

		return contact.getId();
	}

	public long updateContact(ContactDto contactDto) throws Exception {

		Optional<Contact> contact = contactRepository.findById(contactDto.getId());

		if (contact.isEmpty())
			throw new RuntimeException(ConstantMessage.CONTACT_NOT_FOUND);

		contact.get().setContactNo(contactDto.getContactNo());
		contact.get().setFirstName(contactDto.getFirstName());
		contact.get().setLastName(contactDto.getLastName());
		contact.get().setEmail(contactDto.getEmail());

		return (contactRepository.save(contact.get()).getId());

	}
}
