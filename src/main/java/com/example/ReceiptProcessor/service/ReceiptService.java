package com.example.ReceiptProcessor.service;

import com.example.ReceiptProcessor.exception.ReceiptNotFoundException;
import com.example.ReceiptProcessor.model.Receipt;
import com.example.ReceiptProcessor.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.ReceiptProcessor.utils.Utils.calculatePoints;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public String processReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public int getPoints(String id) {
        Receipt receipt = receiptRepository.findById(id);
        if (receipt != null) {
            return calculatePoints(receipt);
        } else {
            throw new ReceiptNotFoundException("Receipt with ID " + id + " not found"); // Indicate not found
        }
    }
}
