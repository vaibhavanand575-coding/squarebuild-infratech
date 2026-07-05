package com.squarebuild.infratech.model;

import java.time.Instant;

public class EnquiryResponse {
    private final String id;
    private final Instant receivedAt;
    private final String message;

    public EnquiryResponse(String id, Instant receivedAt, String message) {
        this.id = id;
        this.receivedAt = receivedAt;
        this.message = message;
    }

    public String getId() { return id; }
    public Instant getReceivedAt() { return receivedAt; }
    public String getMessage() { return message; }
}
