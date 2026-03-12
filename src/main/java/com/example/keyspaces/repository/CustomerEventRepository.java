package com.example.keyspaces.repository;

import com.example.keyspaces.model.CustomerEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerEventRepository extends CassandraRepository<CustomerEvent, UUID> {

    List<CustomerEvent> findByCustomerId(String customerId);
}
