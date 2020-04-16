package com.example.mailexchange.MailExchange.repository;

import com.example.mailexchange.MailExchange.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRegistryRepository extends JpaRepository<UserRole,Integer> {
}
