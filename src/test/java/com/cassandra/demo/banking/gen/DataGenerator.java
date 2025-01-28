package com.cassandra.demo.banking.gen;

import com.cassandra.demo.banking.model.Customer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DataGenerator {
    private static final int BASE = 1000000;
    private static AtomicInteger customerIdGenerator = new AtomicInteger(1);

    public static Date currentDate = new Date();
    public static String  timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(currentDate);


    public static Customer createRandomCustomer(int noOfCustomers) {

        String customerCounter = BASE + customerIdGenerator.getAndIncrement() + "";

        Customer customer = new Customer();
        customer.setCustomerId(UUID.randomUUID());
        customer.setName("Cust-" + customerCounter);
        customer.setAddress("Address-" + customerCounter);
        customer.setEmail(customerCounter + "@mail.email");
        customer.setPhoneNumber("+1" + customerCounter);
        customer.setCreatedDate(currentDate);
        customer.setLastUpdated(currentDate);

        return customer;
    }


//    @Test
//    public void givenUsingPlainJava_whenGeneratingRandomDoubleUnbounded_thenCorrect() {
//        double generatedDouble = Math.random();
//    }
}
