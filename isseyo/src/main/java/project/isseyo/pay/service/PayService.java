package project.isseyo.pay.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.isseyo.pay.dto.PayDto;
import project.isseyo.pay.mapper.PayMapper;

@Service
public class PayService {
	@Autowired 
	private PayMapper payMapper;
	public PayDto selectPay(PayDto payDto) {
		// TODO Auto-generated method stub
		return payMapper.selectPay(payDto);
	}
	public List<PayDto> selectPayList(PayDto payDto) {
		// TODO Auto-generated method stub
		return payMapper.selectPayList(payDto);
	}
	public void insertPay(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		payMapper.insertPay(hashMap);
		return;
		
	}
	public void updatePay(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		payMapper.updatePay(hashMap);
		return;
	}
	public void deletePay(int pkPaySeq) {
		// TODO Auto-generated method stub
		payMapper.deletePay(pkPaySeq);
		return;
	}
	public int seletPayCount(PayDto payDto) {
		// TODO Auto-generated method stub
		return payMapper.seletPayCount(payDto);
	}
	public List<PayDto> selectPayListJoin(PayDto payDto) {
		// TODO Auto-generated method stub
		return payMapper.selectPayListJoin(payDto);
	}

}
