package com.cassandra.demo.banking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Table(keyspace = "bank", value = "account")
public class Account {

    @PrimaryKeyColumn(name = "account_id", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID accountId;

    @Column("account_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String accountName;

    @Column("account_type")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String accountType;

    @Column("customer_id")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID customerId;

    @Column("account_balance")
    @CassandraType(type = CassandraType.Name.DOUBLE)
    Double balance;

    @Column("created_datetime")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Date createdDate;

    @Column("last_updated")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Date lastUpdated;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
}
