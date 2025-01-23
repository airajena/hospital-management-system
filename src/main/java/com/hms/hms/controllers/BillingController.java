package com.hms.hms.controllers;

import com.hms.hms.models.Bill;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillingController {

    // Creates a new bill record
    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        System.out.println("Create bill");
        return null;
    }

    // Retrieves a list of all bills
    @GetMapping
    public List<Bill> getAllBills() {
        System.out.println("Get all bills");
        return null;
    }

    // Retrieves a specific bill's details by its ID
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        System.out.println("Get bill by ID: " + id);
        return null;
    }

    // Updates an existing bill's details by its ID
    @PutMapping("/{id}")
    public void updateBill(@PathVariable Long id) {
        System.out.println("Update bill by ID: " + id);
    }

    // Deletes a specific bill's record by its ID
    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        System.out.println("Delete bill by ID: " + id);
    }
}
