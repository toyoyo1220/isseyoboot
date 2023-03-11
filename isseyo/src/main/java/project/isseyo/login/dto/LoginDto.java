package project.isseyo.login.dto;

import java.util.Collection;
import java.util.Collections;


import lombok.Data;

@Data
public class LoginDto{
	   
	
	/** 사용자 시퀀스 */
	private int pkUserSeq;
	
	/** 아이디 */
	private String userId;
	
	/** 비밀번호 */
	private String password;
	
	/** 사용자 */
	private String userNm;
	
	/** 사업자등록번호 */
	private String bizNum;
	
	/** 사업명 */
	private String bizNm;
	
	/** 업태 */
	private String bizType;
	
	/** 업종 */
	private String bizItem;
	
	/** 전화번호 */
	private String userPhone;
	
	/** 회사 전화번호 */
	private String bizTel;
	
	/** 회사 팩스 */
	private String bizFax;
	
	/** 회사 우편주소 */
	private String bizZip;
	
	/** 회사 주소 */
	private String bizAddr;
	
	/** 회사 상세주소 */
	private String detailAddr;
	
	/** API 발급 KEY */
	private String bizApiKey;
	
	/** 회사 이메일 */
	private String bizEmail;
	
	/** 등록일자 */
	private String registDt;
	
	/** 비밀번호 수정일자 */
	private String pwdUpdtDt;
	
	/** 삭제여부 */
	private String delYn;
	
	/** 사용여부 */
	private String useYn;


}
