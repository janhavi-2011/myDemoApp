package edu.infosys.inventoryApplication.dao;

import edu.infosys.inventoryApplication.bean.ProductSales;
import edu.infosys.inventoryApplication.bean.StockTransaction;
import java.util.List;

public interface StockTransactionDao {

    public void save(StockTransaction transaction);
    public StockTransaction findStockTransactionById(Long id);
    public Long generateId();
    public List<StockTransaction> showAllTransaction();
    public List<StockTransaction> findTransactionsByType(String type);
    public void removeTransactionById(Long id);
    public List<ProductSales> getProductWiseTotalSale();
    public List<Double> getDemandByProduct(String productId);
}
