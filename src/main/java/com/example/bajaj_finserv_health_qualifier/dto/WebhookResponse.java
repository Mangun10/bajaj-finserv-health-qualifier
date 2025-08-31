package com.example.bajaj_finserv_health_qualifier.dto;

import lombok.Data;

@Data
public class WebhookResponse {
    private String webhook;
    private String accessToken;
}