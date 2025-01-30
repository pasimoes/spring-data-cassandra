package com.cassandra.demo.banking.controller;

import com.cassandra.demo.banking.model.Customer;
import com.cassandra.demo.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;


    /**
     * Find all customers.
     * curl -i -X GET 'http://localhost:9090/api/v1/customer'
     * @return All customers from CUSTOMER table.
     */
    @GetMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    //@Operation(summary = "Find all customers")
    List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Find a customer by name.
     * curl -i -X GET 'http://localhost:9090/api/v1/customer/name/Walsh%20Group'
     * @param name Customer name.
     * @return Customer with the name from CUSTOMER table if found and 200. If not 204.
     */
    @GetMapping("/customer/name/{name}")
//    @Operation(summary = "Find a customer by name")
    ResponseEntity<List<Customer>> findByCustomerByName(@PathVariable String name) {

        List<Customer> customers = customerRepository.findByName(name);

        try {
            if (customers.isEmpty())
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(customers, HttpStatus.OK);
                    //customer.map(customers -> new ResponseEntity<>(customers, HttpStatus.OK))
                    //.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find customer by id.
     * curl -i -X GET 'http://localhost:9090/api/v1/customer/15fed5b8-0359-4119-a80a-c3d82734241e'
     * @param id Customer id.
     * @return Customer with id from CUSTOMERS32 table if found and 200. If not 204.
     */
    @GetMapping("/customer/{id}")
//    @Operation(summary = "Find customer by id")
    ResponseEntity<Customer> findCustomerById(@PathVariable UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        try {
            return customer.map(customers -> new ResponseEntity<>(customers, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find customer by email.
     * curl -i -X GET 'http://localhost:9090/api/v1/customer/email/john%40test.com'
     * @param email Customer email.
     * @return Customer with email from CUSTOMER table if found and 201. If not 204.
     */
    @GetMapping("/customer/email/{email}")
//    @Operation(summary = "Find customer by email")
    ResponseEntity<Customer> findCustomerByEmail(@PathVariable String email) {
        List<Customer> customers = customerRepository.findByEmail(email);
        try {
            if (customers.isEmpty())
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(HttpStatus.OK);
                    //customer.map(customers -> new ResponseEntity<>(customers, HttpStatus.OK))
                    //.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create a customer.
     * curl -i -X POST 'http://localhost:9090/api/v1/customer' \
     * -H 'Content-Type: application/json' \
     * -d '{"id": "000ddd", "name": "john cabbage", "email": "john@mail.com"}'
     * @param customer Customer object.
     * @return Location of customer created and 201 else 204.
     */
    @PostMapping("/customer")
    //@Operation(summary = "Create a customer")
    ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {

        customer.setCustomerId(UUID.randomUUID());
        customer.setCreatedDate(new Date());
        customer.setLastUpdated(new Date());
        var retValue = customerRepository.save(customer);
        log.debug("createCustomer -- retValue : {}", retValue);
        if (retValue != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(customer.getCustomerId())
                    .toUri();
            log.debug("URI : {}", location);
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
