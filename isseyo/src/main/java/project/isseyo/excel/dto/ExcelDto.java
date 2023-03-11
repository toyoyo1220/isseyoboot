package project.isseyo.excel.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExcelDto {
	/**
	 * 파일
	 */
	private MultipartFile file;
	
	/**
	 * 파일_ID
	 */
	private String pkFileSeq;
	
	/**
	 * 파일_원본_이름
	 */
	private String fileOriginalName;

	/**
	 * 파일_확장자
	 */
	private String fileExtension;

	/**
	 * 파일_크기
	 */
	private String fileSize;

	/**
	 * 저장소_파일_경로
	 */
	private String filePath;

	/**
	 * 사용_여부 (Y:사용, N:미사용)
	 */
	private String useYn;

	/**
	 * 작성_ID
	 */
	private String registId;

	/**
	 *작성_일자
	 */
	private String registDt;

	/**
	 * 수정_ID
	 */
	private String updateId;

	/**
	 * 수정_일자
	 */
	private String updateDt;

}
