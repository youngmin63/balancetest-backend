package com.example.balancetest.service;

import java.time.LocalDateTime;

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
}