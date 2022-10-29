package com.pratice.quizzApp.repository;

import com.pratice.quizzApp.models.Role;
import com.pratice.quizzApp.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}