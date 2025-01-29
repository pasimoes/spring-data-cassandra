package com.cassandra.demo.banking.controller;

import com.cassandra.demo.banking.model.Account;
import com.cassandra.demo.banking.model.Journal;
import com.cassandra.demo.banking.repository.AccountRepository;
import com.cassandra.demo.banking.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JournalRepository journalRepository;

    /**
     * Get all Accounts.
     *
     * @return List off accounts
     */
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    /**
     * Create an account.
     *
     * @param account Account object.
     * @return Returns HTTP Status code or the URI of the created object.
     */
    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        boolean exists = accountRepository.existsById(account.getAccountId());

        if (!exists) {
            try {
                Account newAccount = accountRepository.save(account);//accountRepository.insert(account);

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newAccount.getAccountId())
                        .toUri();
                return ResponseEntity.created(location).build();
            } catch (Exception e) {
                return new ResponseEntity<>(account, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(account, HttpStatus.CONFLICT);
        }
    }

    /**
     * Find an account by Account Id.
     *
     * @param accountId Account Id
     * @return An account
     */
    @GetMapping("/account/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountId") UUID accountId) {
        Optional<Account> accountData = accountRepository.findById(accountId);
        try {
            return accountData.map(account -> new ResponseEntity<>(account, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find an account by customer Id.
     *
     * @param customerId Customer Id
     * @return A list opf Account(s)
     */
    @GetMapping("/account/getAccounts/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable("customerId") UUID customerId) {
        try {
            List<Account> accountData = new ArrayList<Account>();
            accountData.addAll(accountRepository.findByCustomerId(customerId));
            if (accountData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(accountData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete an Account with specific Id.
     *
     * @param accountId Account ID
     * @return HTTP Status Code
     */
    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("accountId") UUID accountId) {
        try {
            accountRepository.deleteById(accountId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get transactions for an Account Id.
     *
     * @param accountId Account Id
     * @return List of Journal object(s)
     */
    @GetMapping("/account/{accountId}/transactions")
    public ResponseEntity<List<Journal>> getTransactions(@PathVariable("accountId") UUID accountId) {
        try {
            List<Journal> journals = new ArrayList<Journal>();
            journals.addAll(journalRepository.findByAccountId(accountId));
            if (journals.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(journals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create a Journal entry.
     *
     * @param journalEntry object
     * @return HTTP Status Code
     */
    @PostMapping("/account/transaction")
    public ResponseEntity<Journal> postSimpleTransactionEntry(@RequestBody Journal journalEntry) {
        boolean exists = journalRepository.existsById(journalEntry.getJournalId());
        if (!exists) {
            try {
                Journal newJournalEntry = journalRepository.save(journalEntry);
                return new ResponseEntity<>(newJournalEntry, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(journalEntry, HttpStatus.CONFLICT);
        }
    }

    /**
     * Find Journal entries by Account Id.
     *
     * @param accountId Account Id
     * @return Journal object(s)
     */
    @GetMapping("/account/{accountId}/transaction")
    public List<Journal> getTransactionEntriesForAccount(@PathVariable("accountId") UUID accountId) {
        return journalRepository.findByAccountId(accountId);
    }

    /**
     * Clears the Journal Entry.
     *
     * @param transactionId Journal Id
     * @return HTTP Status Code
     */
    @DeleteMapping("/account/transaction/{transactionId}")
    public ResponseEntity<Journal> clearTransactionEntry(@PathVariable UUID transactionId) {
        try {
            boolean exists = journalRepository.existsById(transactionId);
            Optional<Journal> data = journalRepository.findById(transactionId);
            if (data.isPresent()) {
                Journal newJournalEntry = data.get();
                newJournalEntry.setJournalType("DEPOSIT");
                journalRepository.save(newJournalEntry);
                return new ResponseEntity<Journal>(newJournalEntry, HttpStatus.OK);
            } else {
                return new ResponseEntity<Journal>(new Journal(), HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
