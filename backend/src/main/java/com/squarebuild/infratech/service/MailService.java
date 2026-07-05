package com.squarebuild.infratech.service;

import com.squarebuild.infratech.entity.EnquiryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender mailSender;

    @Value("${app.mail.enabled}")
    private boolean mailEnabled;

    @Value("${app.mail.from}")
    private String from;

    @Value("${app.mail.notify-to}")
    private String notifyTo;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Notifies the business of a new enquiry. Never throws — a misconfigured
     * or absent SMTP server must not prevent the lead from being saved.
     */
    public void notifyNewEnquiry(EnquiryEntity enquiry) {
        if (!mailEnabled) {
            log.info("Mail notifications disabled (app.mail.enabled=false); skipping email for enquiry {}", enquiry.getId());
            return;
        }
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(from);
            mail.setTo(notifyTo);
            mail.setSubject("New enquiry from " + enquiry.getName());
            mail.setText(buildBody(enquiry));
            mailSender.send(mail);
        } catch (Exception e) {
            log.warn("Failed to send enquiry notification email for enquiry {}: {}", enquiry.getId(), e.getMessage());
        }
    }

    private String buildBody(EnquiryEntity enquiry) {
        StringBuilder sb = new StringBuilder();
        sb.append("New enquiry received\n\n");
        sb.append("Name: ").append(enquiry.getName()).append('\n');
        sb.append("Email: ").append(enquiry.getEmail()).append('\n');
        sb.append("Phone: ").append(enquiry.getPhone()).append('\n');
        if (enquiry.getProperty() != null) {
            sb.append("Property: ").append(enquiry.getProperty().getTitle()).append('\n');
        }
        if (enquiry.getSource() != null) {
            sb.append("Source: ").append(enquiry.getSource()).append('\n');
        }
        if (enquiry.getMessage() != null && !enquiry.getMessage().isBlank()) {
            sb.append("Message: ").append(enquiry.getMessage()).append('\n');
        }
        return sb.toString();
    }
}
