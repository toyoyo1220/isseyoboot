package project.isseyo.stock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.isseyo.stock.dto.StockDto;
import project.isseyo.stock.mapper.StockMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService{

    private final StockMapper stockMapper;

    @Override
    public void saveStock(StockDto stockDto) {
        stockMapper.saveStock(stockDto);
    }

    @Override
    public List<StockDto> findAllStock() {
        return stockMapper.findAllByStock();
    }

    @Override
    public int countByStock() {
        return 0;
    }

    @Override
    public StockDto findByStock(int id) {
        return stockMapper.findById(id);
    }

    @Override
    public int update(StockDto stockDto) {
        int id = stockMapper.updateById(stockDto);
        return id;
    }

    @Override
    public void delete(int id) {
        stockMapper.deleteById(id);
    }
}
