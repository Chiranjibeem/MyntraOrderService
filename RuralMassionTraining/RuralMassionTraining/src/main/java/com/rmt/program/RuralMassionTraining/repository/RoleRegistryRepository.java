package com.rmt.program.RuralMassionTraining.repository;

import com.rmt.program.RuralMassionTraining.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRegistryRepository extends JpaRepository<UserRole,Integer> {
}
