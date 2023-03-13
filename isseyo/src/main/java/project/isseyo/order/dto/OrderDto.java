package project.isseyo.order.dto;

import lombok.Data;
import project.isseyo.product.dto.ProductDto;

@Data
public class OrderDto extends ProductDto{
	
	/** 주문 정보 SEQ */
	private int pkOrderSeq;
	
	/** 주문코드 */
	private String orderCode;
	
	/** 주문명 */
	private String orderName;
	
	/** 주문 상태 */
	private String orderState;
	
	/** 등록 아이디 */
	private String registId;
	
	/** 등록 일시 */
	private String registDt;
	
	/** 품목정보 SEQ */
	private int pkProductSeq;
	
	/** 사용자 SEQ */
	private int pkUserSeq;
}
