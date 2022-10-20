package com.fairybank.bankingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fairybank.bankingapplication.entity.DepositEntity;

public interface DepositRepository extends JpaRepository<DepositEntity, Integer>{
	
}
