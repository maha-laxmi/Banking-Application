package com.fairybank.bankingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fairybank.bankingapplication.entity.TransactionHistoryEntity;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistoryEntity, Integer> {
	List<TransactionHistoryEntity> findByAddAccountEntityId(int addAccountEntity);
}