package com.example.balancetest.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

// 나이대별 기록 조회 
public Map<String, Double> getAverageByAgeGroup() {
    List<BalanceTestResult> allResults = repository.findAll();
    Map<String, List<BalanceTestResult>> grouped = new HashMap<>();

    grouped.put("10대", new ArrayList<>());
    grouped.put("20대", new ArrayList<>());
    grouped.put("30대", new ArrayList<>());
    grouped.put("40대", new ArrayList<>());
    grouped.put("50대 이상", new ArrayList<>());

    for (BalanceTestResult result : allResults) {
        int age = result.getAge();
        if (age < 20) grouped.get("10대").add(result);
        else if (age < 30) grouped.get("20대").add(result);
        else if (age < 40) grouped.get("30대").add(result);
        else if (age < 50) grouped.get("40대").add(result);
        else grouped.get("50대 이상").add(result);
    }

    Map<String, Double> averageMap = new HashMap<>();
    for (String key : grouped.keySet()) {
        List<BalanceTestResult> group = grouped.get(key);
        double avg = group.stream()
                .mapToDouble(BalanceTestResult::getDurationSeconds)
                .average()
                .orElse(0.0);
        averageMap.put(key, avg);
    }

    return averageMap;
}

public boolean deleteResult(Long id) {
    if (repository.existsById(id)) {
        repository.deleteById(id);
        return true;
    }
    return false;
}

   
}