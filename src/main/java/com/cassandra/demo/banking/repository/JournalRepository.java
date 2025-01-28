package com.cassandra.demo.banking.repository;

import com.cassandra.demo.banking.model.Journal;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RestResource(exported = false)
public interface JournalRepository extends CassandraRepository<Journal, UUID> {
    Journal findJournalByJournalId(UUID journalId);

    List<Journal> findByAccountId(UUID accountId);
}
