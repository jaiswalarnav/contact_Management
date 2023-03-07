package com.contact.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.contact.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	Contact findByContactNo(String contactNo);

	List<Contact> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
			String searchKey, String searchKey2, String searchKey3);
}
