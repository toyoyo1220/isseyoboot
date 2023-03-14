package project.isseyo.order.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.isseyo.login.mapper.LoginMapper;
import project.isseyo.order.dto.OrderDto;
import project.isseyo.order.mapper.OrderMapper;

@Service
public class OrderService {
	@Autowired 
	private OrderMapper orderMapper;

	public OrderDto selectOrder(OrderDto orderDto) {
		// TODO Auto-generated method stub
		return orderMapper.selectOrder(orderDto);
	}

	public void insertOrder(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		orderMapper.insertOrder(hashMap);
		return;
	}

	public List<OrderDto> selectOrderList(OrderDto orderDto) {
		// TODO Auto-generated method stub
		return orderMapper.selectOrderList(orderDto);
	}

	public void updateOrder(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		orderMapper.updateOrder(hashMap);
		return;
	}

	public void deleteOrder(int pkOrderSeq) {
		orderMapper.deleteOrder(pkOrderSeq);
		return;
		
	}

	public int seletOrderCount(OrderDto orderDto) {
		// TODO Auto-generated method stub
		return orderMapper.seletOrderCount(orderDto);
	}
}
