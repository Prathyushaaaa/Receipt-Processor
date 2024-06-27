package com.example.ReceiptProcessor.repository;

import com.example.ReceiptProcessor.model.Receipt;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ReceiptRepository {
    private final Map<String, Receipt> receipts = new HashMap<>();

    public String save(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipts.put(id, receipt);
        return id;
    }

    public Receipt findById(String id) {
        return receipts.get(id);
    }
}