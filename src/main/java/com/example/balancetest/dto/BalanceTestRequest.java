package com.example.balancetest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceTestRequest {
    private String name;
    private int age;
    private String gender;
    private double durationSeconds;
}