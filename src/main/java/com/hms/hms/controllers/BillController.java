package com.hms.hms.controllers;

import com.hms.hms.models.Bill;
import com.hms.hms.services.BillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    private static final Logger logger = LogManager.getLogger(BillController.class);
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    // Creates a new bill record
    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        try {
            logger.info("Request received to create a new bill");
            Bill createdBill = billService.createBill(bill);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBill);
        } catch (Exception e) {
            logger.error("Error creating bill: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Retrieves a list of all bills
    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        try {
            logger.info("Request received to fetch all bills");
            List<Bill> bills = billService.getAllBills();
            return ResponseEntity.ok(bills);
        } catch (Exception e) {
            logger.error("Error fetching bills: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Retrieves a specific bill's details by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        try {
            logger.info("Request received to fetch bill with ID: {}", id);
            Bill bill = billService.getBillById(id);
            return ResponseEntity.ok(bill);
        } catch (RuntimeException e) {
            logger.warn("Bill not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error fetching bill by ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Updates an existing bill's details by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        try {
            logger.info("Request received to update bill with ID: {}", id);
            Bill updatedBill = billService.updateBill(id, bill);
            return ResponseEntity.ok(updatedBill);
        } catch (RuntimeException e) {
            logger.warn("Bill not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error updating bill with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Deletes a specific bill's record by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        try {
            logger.info("Request received to delete bill with ID: {}", id);
            billService.deleteBill(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.warn("Bill not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error deleting bill with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
