package edu.infosys.inventoryApplication.controller;

import edu.infosys.inventoryApplication.bean.Product;
import edu.infosys.inventoryApplication.bean.ProductSales;
import edu.infosys.inventoryApplication.bean.StockTransaction;
import edu.infosys.inventoryApplication.dao.ProductDao;
import edu.infosys.inventoryApplication.dao.StockTransactionDao;
import edu.infosys.inventoryApplication.service.StockTransactionService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory/")
@CrossOrigin(origins = "http://localhost:3838")
public class StockTransactionController {

    @Autowired
    private StockTransactionDao transactionDao;
    
    @Autowired
    private StockTransactionService transactionService;  // Add this
    
    @Autowired
    private ProductDao productDao;  // Add this if needed

    @PostMapping("/stock")
    public void save(@RequestBody StockTransaction transaction) {
        // Add validation
        if (transaction.getProductId() == null || transaction.getProductId().isEmpty()) {
            throw new IllegalArgumentException("Product ID is required");
        }
        if (transaction.getUserId() == null || transaction.getUserId().isEmpty()) {
            throw new IllegalArgumentException("User ID is required");
        }
        
        // Set the rate if not provided
        if (transaction.getRate() == null) {
            Product product = productDao.findProductById(transaction.getProductId());
            if ("IN".equalsIgnoreCase(transaction.getTransactionType())) {
                transaction.setRate(product.getPurchasePrice());
            } else {
                transaction.setRate(product.getSalesPrice());
            }
        }
        
        transactionDao.save(transaction);
    }
}