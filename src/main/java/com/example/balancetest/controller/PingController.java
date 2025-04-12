package com.example.balancetest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Render 서버 깨우기용 Ping API
 */
@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }


    
}
//안녕