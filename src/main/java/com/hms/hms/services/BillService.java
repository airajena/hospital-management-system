package com.hms.hms.services;

import com.hms.hms.models.Bill;
import com.hms.hms.repositories.BillRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BillService {

    private static final Logger logger = LogManager.getLogger(BillService.class);
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill createBill(Bill bill) {
        try {
            logger.info("Creating a new bill for patient ID: {}", bill.getPatientId());
            return billRepository.save(bill);
        } catch (Exception e) {
            logger.error("Error creating bill: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create bill", e);
        }
    }

    public List<Bill> getAllBills() {
        try {
            logger.info("Fetching all bills");
            return billRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all bills: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch bills", e);
        }
    }

    public Bill getBillById(Long id) {
        try {
            logger.info("Fetching bill with ID: {}", id);
            return billRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Bill not found with ID: " + id));
        } catch (Exception e) {
            logger.error("Error fetching bill by ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch bill by ID", e);
        }
    }

    public Bill updateBill(Long id, Bill updatedBill) {
        try {
            logger.info("Updating bill with ID: {}", id);
            Bill existingBill = billRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Bill not found with ID: " + id));
            existingBill.setTotal(updatedBill.getTotal());
            existingBill.setStatus(updatedBill.getStatus());
            return billRepository.save(existingBill);
        } catch (Exception e) {
            logger.error("Error updating bill with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to update bill", e);
        }
    }

    public void deleteBill(Long id) {
        try {
            logger.info("Deleting bill with ID: {}", id);
            if (!billRepository.existsById(id)) {
                throw new RuntimeException("Bill not found with ID: " + id);
            }
            billRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting bill with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to delete bill", e);
        }
    }
}
