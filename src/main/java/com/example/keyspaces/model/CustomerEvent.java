package com.example.keyspaces.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("customer_events")
public class CustomerEvent {

    @PrimaryKey
    private UUID id;

    @Column("customer_id")
    private String customerId;

    @Column("event_type")
    private String eventType;

    @Column("created_at")
    private Instant createdAt;

    public CustomerEvent() {
    }

    public CustomerEvent(UUID id, String customerId, String eventType, Instant createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.eventType = eventType;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
