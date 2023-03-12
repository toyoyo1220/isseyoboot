package project.isseyo.stock.service;

import project.isseyo.product.dto.ProductDto;
import project.isseyo.stock.dto.StockDto;

import java.util.List;

public interface StockService {

    void saveStock(StockDto stockDto);
    List<StockDto> findAllStock();
    int countByStock();
    StockDto findByStock(int id);
    int update(StockDto stockDto);
    void delete(int id);

}
