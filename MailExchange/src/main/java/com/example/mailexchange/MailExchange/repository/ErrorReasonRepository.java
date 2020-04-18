package com.example.mailexchange.MailExchange.repository;

import com.example.mailexchange.MailExchange.model.ErrorReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorReasonRepository extends JpaRepository<ErrorReason,Integer> {
}
