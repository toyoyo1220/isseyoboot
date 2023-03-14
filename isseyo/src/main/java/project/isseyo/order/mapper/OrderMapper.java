package project.isseyo.order.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.isseyo.order.dto.OrderDto;

@Mapper
public interface OrderMapper {

	OrderDto selectOrder(OrderDto orderDto);

	void insertOrder(HashMap<String, Object> hashMap);

	List<OrderDto> selectOrderList(OrderDto orderDto);

	void updateOrder(HashMap<String, Object> hashMap);

	void deleteOrder(int pkOrderSeq);

	int seletOrderCount(OrderDto orderDto);

}
