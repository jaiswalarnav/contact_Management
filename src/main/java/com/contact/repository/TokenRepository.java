package com.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.contact.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
