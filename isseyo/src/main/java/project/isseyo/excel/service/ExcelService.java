package project.isseyo.excel.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.isseyo.excel.dto.ExcelDto;

@Service
public class ExcelService {
	
	/**
	 * 엑셀 업로드.
	 * @param fileVo - 등록할 정보가 담긴 FileVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public List<HashMap<Integer, Object>> excelUpload(ExcelDto excelDto) throws Exception {
		MultipartFile multipartFile = null;
		multipartFile = excelDto.getFile();
		
		/*if (multipartFile == null || multipartFile.isEmpty()) {
			throw new UploadException("fileCreate - multipartFile : null");
		}*/
		
		//엑셀정보
		ExcelUtil eu = new ExcelUtil();
		int sheetNum = 0;		//1번째 시트 읽음 
		int strartRowNum = 0;	//2번째 줄부터 읽음
		int startCelNum = 0; 	//3번째 줄부터 읽음(지역ID)
		List<HashMap<Integer, Object>> excelList = null;
		try {
			excelList = eu.excelReadSetValue(multipartFile, sheetNum, strartRowNum, startCelNum);
			System.out.println(excelList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excelList;
	}
	
}
