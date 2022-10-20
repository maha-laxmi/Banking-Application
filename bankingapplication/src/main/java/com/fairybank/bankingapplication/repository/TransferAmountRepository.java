package com.fairybank.bankingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fairybank.bankingapplication.entity.TransferAmountEntity;
import java.util.Optional;

public interface TransferAmountRepository extends JpaRepository<TransferAmountEntity, Integer>{
    Optional<TransferAmountEntity> findAccountById(int accountId);

}
