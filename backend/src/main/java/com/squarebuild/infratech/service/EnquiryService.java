package com.squarebuild.infratech.service;

import com.squarebuild.infratech.model.EnquiryRequest;
import com.squarebuild.infratech.model.EnquiryResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class EnquiryService {

    private final List<EnquiryRequest> enquiries = new CopyOnWriteArrayList<>();

    public EnquiryResponse submit(EnquiryRequest request) {
        enquiries.add(request);
        return new EnquiryResponse(
                UUID.randomUUID().toString(),
                Instant.now(),
                "Thanks " + request.getName() + ", our team will reach out within 24 hours."
        );
    }

    public List<EnquiryRequest> findAll() {
        return new ArrayList<>(enquiries);
    }
}
