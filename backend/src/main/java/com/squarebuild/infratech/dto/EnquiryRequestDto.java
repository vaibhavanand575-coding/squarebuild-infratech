package com.squarebuild.infratech.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EnquiryRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9+\\-\\s]{7,15}$", message = "Enter a valid phone number")
    private String phone;

    private String message;

    /** Optional — resolved server-side to the property relation. */
    private String propertySlug;

    /** Where the enquiry came from, e.g. "contact_page", "property_detail". */
    private String source;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getPropertySlug() { return propertySlug; }
    public void setPropertySlug(String propertySlug) { this.propertySlug = propertySlug; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
