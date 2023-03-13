package project.isseyo.pay.dto;

import lombok.Data;
import project.isseyo.product.dto.ProductDto;

@Data
public class PayDto extends ProductDto {
	/** 결제 정보 SEQ */
	private int pkPaySeq;
	
	/** 결제코드 */
	private String payCode;
	
	/** 결제 방법 */
	private String payMethod;
	
	/** 결제 상태 */
	private String payState;
	
	/** 등록 아이디 */
	private String registId;
	
	/** 등록 일시 */
	private String registDt;
	
	/** 품목정보 SEQ */
	private int pkProductSeq;
	
	/** 사용자 SEQ */
	private int pkUserSeq;
}
