package com.fairybank.bankingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fairybank.bankingapplication.entity.SignInEntity;

public interface SignInRepository extends JpaRepository<SignInEntity, Integer>{
	Optional<SignInEntity> findByUserName(String name);
	boolean existsByUserName(String userName);

}
