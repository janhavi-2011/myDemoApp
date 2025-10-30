package edu.infosys.inventoryApplication.service;

import edu.infosys.inventoryApplication.bean.Product;
import edu.infosys.inventoryApplication.bean.StockTransaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class StockTransactionService {

    public StockTransaction createTransaction(Product product, Double quantity, String transactionType, String userId) {
        
        StockTransaction transaction = new StockTransaction();
        transaction.setProductId(product.getProductId());
        
        transaction.setQuantity(quantity);
        transaction.setTransactionType(transactionType);
        transaction.setUserId(userId);

        double transactionValue = 0.0;
        if ("IN".equalsIgnoreCase(transactionType)) {
            transactionValue = quantity * product.getPurchasePrice();
        } else if ("OUT".equalsIgnoreCase(transactionType)) {
            transactionValue = quantity * product.getSalesPrice();
        }
        transaction.setTransactionValue(transactionValue);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        transaction.setTransactionDate(formattedDateTime);
        
        return transaction;
    }
}