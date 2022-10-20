package com.fairybank.bankingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fairybank.bankingapplication.entity.RegisterEntity;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Integer>{
	Optional<RegisterEntity> findByAadharCard(Long aadLong);

}
