package com.example.keyspaces.service;

import com.example.keyspaces.model.CustomerEvent;
import com.example.keyspaces.repository.CustomerEventRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerEventService {

    private final CustomerEventRepository repository;

    public CustomerEventService(CustomerEventRepository repository) {
        this.repository = repository;
    }

    public CustomerEvent createEvent(String customerId, String eventType) {
        CustomerEvent event = new CustomerEvent(UUID.randomUUID(), customerId, eventType, Instant.now());
        return repository.save(event);
    }

    public List<CustomerEvent> listByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }
}
