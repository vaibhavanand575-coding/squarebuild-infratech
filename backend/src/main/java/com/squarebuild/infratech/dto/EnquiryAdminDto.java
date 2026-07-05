package com.squarebuild.infratech.dto;

import java.time.Instant;

public class EnquiryAdminDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String message;
    private String propertyTitle;
    private String source;
    private Instant createdAt;

    public EnquiryAdminDto(Long id, String name, String email, String phone, String message,
                            String propertyTitle, String source, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.propertyTitle = propertyTitle;
        this.source = source;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getMessage() { return message; }
    public String getPropertyTitle() { return propertyTitle; }
    public String getSource() { return source; }
    public Instant getCreatedAt() { return createdAt; }
}
