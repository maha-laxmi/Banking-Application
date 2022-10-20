package com.fairybank.bankingapplication.repository;

import java.util.List;
import java.util.Optional;

import com.fairybank.bankingapplication.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fairybank.bankingapplication.entity.AddAccountEntity;

@Repository
public interface AddAccountRepository extends JpaRepository<AddAccountEntity, Integer>{
	Optional<AddAccountEntity> findByAccountNumber(long accountNumber);
	List<AddAccountEntity> findByRegisterEntity(RegisterEntity registerEntity);
}
