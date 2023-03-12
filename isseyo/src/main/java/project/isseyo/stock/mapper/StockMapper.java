package project.isseyo.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import project.isseyo.stock.dto.StockDto;

import java.util.List;

@Mapper
public interface StockMapper {
    List<StockDto> findAllByStock();
    void saveStock(StockDto stockDto);

    StockDto findById(int id);
    int updateById(StockDto stockDto);
    void deleteById(int id);
}
