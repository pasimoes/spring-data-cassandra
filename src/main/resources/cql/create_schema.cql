
CREATE KEYSPACE IF NOT EXISTS bank
   WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };

USE bank;

# Create the Account Table:
CREATE TABLE IF NOT EXISTS bank.account (
	account_id UUID,
	account_name TEXT,
	account_type TEXT,
	customer_id UUID,
    account_balance DOUBLE,
    created_datetime TIMESTAMP,
    last_updated TIMESTAMP,
    PRIMARY KEY(account_id, customer_id)
) WITH compaction= { 'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy' }
  AND compression = {'sstable_compression' : ''}
  AND caching = {'keys':'NONE', 'rows_per_partition':'NONE'};

# Create the Customer Table:
CREATE TABLE IF NOT EXISTS bank.customer (
	customer_id UUID,
	name TEXT,
    address TEXT,
    phone_number TEXT,
    email TEXT,
    created_datetime TIMESTAMP,
    last_updated TIMESTAMP,
    PRIMARY KEY(customer_id)
) WITH compaction= { 'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy' }
  AND compression = {'sstable_compression' : ''}
  AND caching = {'keys':'NONE', 'rows_per_partition':'NONE'};


# Create the Journal Table:
CREATE TABLE IF NOT EXISTS bank.journal (
    journal_id UUID,
    account_id UUID,
    journal_amount DOUBLE,
    journal_type TEXT,
    journal_time TIMESTAMP,
    merchant_id UUID,
    location TEXT,
    fraud_status BOOLEAN,
    PRIMARY KEY(journal_id)
) WITH compaction= { 'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy' }
  AND compression = {'sstable_compression' : ''}
  AND caching = {'keys':'NONE', 'rows_per_partition':'NONE'};
