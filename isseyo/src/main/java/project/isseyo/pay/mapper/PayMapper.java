package project.isseyo.pay.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.isseyo.pay.dto.PayDto;

@Mapper
public interface PayMapper {

	PayDto selectPay(PayDto payDto);

	void insertPay(HashMap<String, Object> hashMap);

	List<PayDto> selectPayList(PayDto payDto);

	void updatePay(HashMap<String, Object> hashMap);

	void deletePay(int pkPaySeq);

}
