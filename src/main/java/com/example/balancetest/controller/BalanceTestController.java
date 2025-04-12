package com.example.balancetest.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.balancetest.dto.BalanceTestRequest;
import com.example.balancetest.entity.BalanceTestResult;
import com.example.balancetest.service.BalanceTestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://youngmin63.github.io")
 // 프론트엔드에서 요청 허용
public class BalanceTestController {

    private final BalanceTestService service;

    @PostMapping("/submit")
    public String saveResult(@RequestBody BalanceTestRequest request) {
        System.out.println("🔥 저장 요청 받음: " + request.getName() + ", " + request.getDurationSeconds());

        service.saveTestResult(request);
        return "성공적으로 저장되었습니다.";
    }
    
    // 전체 기록 조회
    @GetMapping("/results")
    public List<BalanceTestResult> getAllResults() {
    return service.getAllResults();
}

// 특정 날짜 기록 조회
@GetMapping("/results/by-date")
public List<BalanceTestResult> getResultsByDate(@RequestParam(required = false) String date) {
    if (date != null) {
        return service.getResultsByDate(LocalDate.parse(date));
    } else {
        return service.getAllResults();
    }
}
// 최근 7일 기록 조회
@GetMapping("/results/recent")
public List<BalanceTestResult> getRecentResults() {
    return service.getRecentResults();
}

//평균 기록 조회 
@GetMapping("/results/average")
public Map<String, Double> getAverageDuration() {
    double avg = service.getAverageDuration();
    return Map.of("averageDuration", avg);
}

//나이대별 평균 기록 조회
@GetMapping("/results/average-by-age-group")
public Map<String, Double> getAverageByAgeGroup() {
    return service.getAverageByAgeGroup();
}

//기록 삭제 
@DeleteMapping("/results/{id}")
public ResponseEntity<String> deleteResult(
        @PathVariable Long id,
        @RequestParam String userId) {

    boolean deleted = service.deleteResult(id, userId);
    if (deleted) {
        return ResponseEntity.ok("삭제되었습니다.");
    } else {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한이 없습니다.");
    }
}
//유저별 기록 조회
@GetMapping("/results/by-user")
public List<BalanceTestResult> getResultsByUser(@RequestParam String userId) {
    return service.getResultsByUserId(userId);
}
}
