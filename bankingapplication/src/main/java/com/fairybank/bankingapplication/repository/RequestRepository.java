package com.fairybank.bankingapplication.repository;

import com.fairybank.bankingapplication.entity.RegisterEntity;
import com.fairybank.bankingapplication.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<RequestEntity,Integer> {
    Optional<List<RequestEntity>> findByToUser(RegisterEntity registerEntity);
    Optional<List<RequestEntity>> findByRequestUser(RegisterEntity registerEntity);
}
