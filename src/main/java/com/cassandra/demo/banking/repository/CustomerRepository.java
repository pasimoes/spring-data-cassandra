package com.cassandra.demo.banking.repository;

import com.cassandra.demo.banking.model.Customer;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RestResource(exported = false)
public interface CustomerRepository  extends CassandraRepository<Customer, UUID> {

    @AllowFiltering
    List<Customer> findByName(final String name);

    @AllowFiltering
    List<Customer> findByEmail(final String email);
}

