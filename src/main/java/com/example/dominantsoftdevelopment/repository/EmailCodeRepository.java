package com.example.dominantsoftdevelopment.repository;


import com.example.dominantsoftdevelopment.model.EmailCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailCodeRepository extends CrudRepository<EmailCode, String> {
    Optional<EmailCode> findEmailCodeByEmail(String email);
}
