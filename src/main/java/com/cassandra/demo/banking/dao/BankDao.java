package com.cassandra.demo.banking.dao;

import com.datastax.oss.driver.api.core.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class BankDao {

    private static Logger logger = LoggerFactory.getLogger(BankDao.class);

    private Session session;

    private static String keyspaceName = "bank";

    private static String transactionTable = keyspaceName + ".transaction";
    private static String accountsTable = keyspaceName + ".account";
    private static String customersTable = keyspaceName + ".customer";


    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

}
