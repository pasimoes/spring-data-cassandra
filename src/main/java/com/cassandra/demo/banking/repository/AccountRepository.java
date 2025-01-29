package com.cassandra.demo.banking.repository;

import com.cassandra.demo.banking.model.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RestResource(exported = false)
public interface AccountRepository extends CrudRepository<Account, UUID> {


    Account findByAccountId(UUID accountId);

    List<Account> findByCustomerId(UUID customerId);

    List<Account> findByAccountNameContains(String accountName);
}
