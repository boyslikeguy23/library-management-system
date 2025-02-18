package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Copy;

public interface CopyRepository extends JpaRepository<Copy, Integer>
{
	
}
