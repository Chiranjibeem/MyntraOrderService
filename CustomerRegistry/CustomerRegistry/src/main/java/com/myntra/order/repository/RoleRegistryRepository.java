package com.myntra.order.repository;

import com.myntra.order.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRegistryRepository extends JpaRepository<UserRole,String> {
}
