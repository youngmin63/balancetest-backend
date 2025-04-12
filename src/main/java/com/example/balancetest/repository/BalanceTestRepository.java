package com.example.balancetest.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.balancetest.entity.BalanceTestResult;

public interface BalanceTestRepository extends JpaRepository<BalanceTestResult, Long> {
    // 특정 날짜 기록 조회
    List<BalanceTestResult> findByTestDateBetween(LocalDateTime start, LocalDateTime end);

    // 최근 7일 기록 조회
    List<BalanceTestResult> findByTestDateAfter(LocalDateTime date);

    //평균 기록 조회 
    @Query("SELECT AVG(r.durationSeconds) FROM BalanceTestResult r")
    double calculateAverageDuration();

    //사용자별 기록 조회 기능 
    List<BalanceTestResult> findByUserId(String userId);




}


