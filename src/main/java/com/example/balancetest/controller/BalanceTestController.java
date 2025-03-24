package com.example.balancetest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.balancetest.dto.BalanceTestRequest;
import com.example.balancetest.service.BalanceTestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // 프론트엔드에서 요청 허용
public class BalanceTestController {

    private final BalanceTestService service;

    @PostMapping
    public String saveResult(@RequestBody BalanceTestRequest request) {
        System.out.println("🔥 저장 요청 받음: " + request.getName() + ", " + request.getDurationSeconds());

        service.saveTestResult(request);
        return "성공적으로 저장되었습니다.";
    }
}
