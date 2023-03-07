package com.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.contact.model.UserRegister;

public interface UserRepository extends JpaRepository<UserRegister, Long> {

	UserRegister findByName(String name);

}
