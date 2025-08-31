package com.example.bajaj_finserv_health_qualifier.runner;

import com.example.bajaj_finserv_health_qualifier.service.ChallengeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final ChallengeService challengeService;

    public AppRunner(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started, running the challenge...");
        challengeService.solveChallenge();
    }
}