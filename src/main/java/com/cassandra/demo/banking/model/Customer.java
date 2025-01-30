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
@Table(keyspace = "bank", value = "customer")
public class Customer {

    @PrimaryKeyColumn(name = "customer_id", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID customerId;

    @Column
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;

    @Column
    @CassandraType(type = CassandraType.Name.TEXT)
    private String address;

    @Column("phone_number")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String phoneNumber;

    @Column
    @CassandraType(type = CassandraType.Name.TEXT)
    private String email;

    @Column("created_datetime")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Date createdDate;

    @Column("last_updated")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Date lastUpdated;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
