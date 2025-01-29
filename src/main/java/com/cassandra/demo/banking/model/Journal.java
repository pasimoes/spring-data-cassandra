package com.cassandra.demo.banking.model;

import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Table(value = "journal")
public class Journal {

    @PrimaryKeyColumn(name = "journal_id", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID journalId;

    @Column("account_id")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID accountId;

    @Column("journal_amount")
    @CassandraType(type = CassandraType.Name.DOUBLE)
    Double amount;

    @Column("journal_time")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Date journalTime;

    @Column("journal_type")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String journalType;

    @Column("merchant_id")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID merchantId;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String location;

    @Column("fraud_status")
    @CassandraType(type = CassandraType.Name.BOOLEAN)
    private Boolean fraudStatus;

    public UUID getJournalId() {
        return journalId;
    }

    public void setJournalId(UUID journalId) {
        this.journalId = journalId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getJournalTime() {
        return journalTime;
    }

    public void setJournalTime(Date journalTime) {
        this.journalTime = journalTime;
    }

    public String getJournalType() {
        return journalType;
    }

    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }

    public UUID getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(UUID merchantId) {
        this.merchantId = merchantId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getFraudStatus() {
        return fraudStatus;
    }

    public void setFraudStatus(Boolean fraudStatus) {
        this.fraudStatus = fraudStatus;
    }
}