package com.example.balancetest.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.balancetest.dto.BalanceTestRequest;
import com.example.balancetest.entity.BalanceTestResult;
import com.example.balancetest.repository.BalanceTestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BalanceTestService {

    private final BalanceTestRepository repository;

    public void saveTestResult(BalanceTestRequest request) {
        BalanceTestResult result = BalanceTestResult.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .durationSeconds(request.getDurationSeconds())
                .testDate(LocalDateTime.now())
                .build();

        repository.save(result);
    }

    // 전체 기록 조회
    public List<BalanceTestResult> getAllResults() {
        return repository.findAll();
    }
//특정 날짜 기록 조회
    public List<BalanceTestResult> getResultsByDate(LocalDate date) {
    return repository.findByTestDateBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
}

// 최근 7일 기록 조회
public List<BalanceTestResult> getRecentResults() {
    LocalDateTime oneWeekAgo = LocalDateTime.now().minusDays(7);
    return repository.findByTestDateAfter(oneWeekAgo);
}

// 평균 기록 조회 
public double getAverageDuration() {
    return repository.calculateAverageDuration();
}

   
}