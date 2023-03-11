package project.isseyo.product.dto;

import lombok.Data;
import project.isseyo.login.dto.LoginDto;

@Data
public class ProductDto extends LoginDto {
	
	/** 품목정보 SEQ */
	private int pkProductSeq;
	
	/** 품목명 */
	private String productName;
	
	/** 품목코드 */
	private String productCode;
	
	/** 규격 */
	private String standard;
	
	/** 단위 */
	private String unit;
	
	/** 이미지 */
	private String productImg;
	
	/** 품목구분 */
	private String divn;
	
	/** 등록ID */
	private String registId;
	
	/** 등록일자 */
	private String registDt;
	
	/** 비고 */
	private String etc;
	
	/** 사용유무 */
	private String useYn;
	
	/** 삭제 유무 */
	private String delYn;
	
	/** 수정 아이디 */
	private String updateId;
	
	/** 수정 일자 */
	private String updateDt;
	
	/** 사용자 시퀀스 */
	private int pkUserSeq;
	
	/** 품목 속성 */
	private String attribute;
	
	/** 품목 값 */
	private String value;

	/** Auto_Increment 값 */
	private int returnId;
}
