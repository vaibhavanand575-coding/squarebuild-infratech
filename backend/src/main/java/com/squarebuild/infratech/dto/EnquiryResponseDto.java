package com.squarebuild.infratech.dto;

import java.time.Instant;

public class EnquiryResponseDto {
    private final Long id;
    private final Instant receivedAt;
    private final String message;

    public EnquiryResponseDto(Long id, Instant receivedAt, String message) {
        this.id = id;
        this.receivedAt = receivedAt;
        this.message = message;
    }

    public Long getId() { return id; }
    public Instant getReceivedAt() { return receivedAt; }
    public String getMessage() { return message; }
}
