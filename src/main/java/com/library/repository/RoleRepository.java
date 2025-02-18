package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Role;
import com.library.model.ERole;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(ERole name);
		
}
