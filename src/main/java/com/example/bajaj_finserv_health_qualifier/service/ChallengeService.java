package com.example.bajaj_finserv_health_qualifier.service;

import com.example.bajaj_finserv_health_qualifier.dto.SolutionRequest;
import com.example.bajaj_finserv_health_qualifier.dto.WebhookRequest;
import com.example.bajaj_finserv_health_qualifier.dto.WebhookResponse;
import com.example.bajaj_finserv_health_qualifier.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ChallengeService {

    private final WebClient webClient;
    private final PaymentsRepository paymentsRepository;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public ChallengeService(WebClient.Builder webClientBuilder, PaymentsRepository paymentsRepository) {
        this.webClient = webClientBuilder.baseUrl(apiBaseUrl).build();
        this.paymentsRepository = paymentsRepository;
    }

    public void solveChallenge() {
        // Step 1: Generate Webhook
        generateWebhook()
                .flatMap(response -> {
                    // Step 2: Solve SQL problem and submit
                    String sqlQuery = solveSqlProblem();
                    return submitSolution(response.getWebhook(), response.getAccessToken(), sqlQuery);
                })
                .subscribe(
                        result -> System.out.println("Successfully submitted solution: " + result),
                        error -> System.err.println("Error during challenge: " + error.getMessage()));
        runQueryLocally();
    }

    private Mono<WebhookResponse> generateWebhook() {
        WebhookRequest request = new WebhookRequest("John Doe", "REG12347", "john@example.com");
        System.out.println("Generating webhook with request: " + request);

        return webClient.post()
                .uri("/generateWebhook/JAVA")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), WebhookRequest.class)
                .retrieve()
                .bodyToMono(WebhookResponse.class)
                .doOnSuccess(response -> System.out.println("Webhook generated: " + response));
    }

    private String solveSqlProblem() {
        // The registration number "REG12347" ends with 47 (odd), so this is Question 1.
        System.out.println("Solving SQL problem for odd registration number...");
        return "SELECT p.AMOUNT AS SALARY, CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, TIMESTAMPDIFF(YEAR, e.DOB, '2025-08-31') AS AGE, d.DEPARTMENT_NAME FROM PAYMENTS p JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID WHERE DAY(p.PAYMENT_TIME) != 1 ORDER BY p.AMOUNT DESC LIMIT 1";
    }

    private Mono<String> submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        SolutionRequest solutionRequest = new SolutionRequest(sqlQuery);
        System.out.println("Submitting solution: " + solutionRequest);

        // Per the instructions, the submission URL is a fixed path.
        String submissionUrl = "/testWebhook/JAVA";

        return WebClient.create(apiBaseUrl).post()
                .uri(submissionUrl)
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(solutionRequest), SolutionRequest.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    public void runQueryLocally() {
        List<Object[]> results = paymentsRepository.findHighestSalaryNotOnFirstDay();
        if (results != null && !results.isEmpty()) {
            Object[] result = results.get(0);
            System.out.println("SALARY: " + result[0]);
            System.out.println("NAME: " + result[1]);
            System.out.println("AGE: " + result[2]);
            System.out.println("DEPARTMENT_NAME: " + result[3]);
        } else {
            System.out.println("No results found.");
        }
    }
}