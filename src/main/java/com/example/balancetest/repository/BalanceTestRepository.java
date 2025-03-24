package com.example.balancetest.repository;

import com.example.balancetest.entity.BalanceTestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceTestRepository extends JpaRepository<BalanceTestResult, Long> {
}