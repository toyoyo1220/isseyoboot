package project.isseyo.excel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import project.isseyo.excel.dto.ExcelDto;
import project.isseyo.excel.service.ExcelService;
import project.isseyo.login.dto.LoginDto;
import project.isseyo.login.service.LoginService;
import project.isseyo.product.service.ProductService;

@Controller
public class ExcelController {
	
	@Autowired
    private ExcelService excelService;
	
	@Autowired
    private ProductService productService;
	
	/**
	 * 파일 업로드
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/excelUpload")
	public String excelUpload(@ModelAttribute("excelDto") ExcelDto excelDto, ModelMap model, HttpServletRequest request )
			throws Exception {
			MultipartFile uploadFile = excelDto.getFile();
			List<HashMap<Integer, Object>> sampleList = excelService.excelUpload(excelDto);
			HashMap<Integer, Object> columns = null;
			HashMap<Integer, Object> values = null;
			HashMap<String, Object> productMap = null;
			ArrayList<HashMap<String, Object>> productDetailArray = null;
			LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
			columns = sampleList.get(0);
			
			for (int i = 1; i < sampleList.size(); i++) {
				productMap = new HashMap<String, Object>();
				productDetailArray = new ArrayList<HashMap<String, Object>>();
				
				for (int j = 0; j < sampleList.get(i).size(); j++) {
					HashMap<String, Object> productDetailMap = new HashMap<String, Object>();
					values = sampleList.get(i);
					switch (j) { 
					case 0: 
						productMap.put("productName", values.get(j));
						break; 
					case 1: 
						productMap.put("productCode", values.get(j)); 
						break; 
					case 2:
						productMap.put("standard", values.get(j));
						break; 
					case 3: 
						productMap.put("unit", values.get(j));
						break; 
					case 4: 
						if(!values.get(j).equals("null")) {
						productMap.put("productImg", values.get(j));
						}
						break; 
					case 5:
						productMap.put("divn", values.get(j));
						break;
					case 6: 
						if(!values.get(j).equals("null")) {
							productMap.put("etc", values.get(j));
						}
						break; 
					default:
						if(!values.get(j).equals("null")) {
							productDetailMap.put("attribute", columns.get(j));
							productDetailMap.put("value", values.get(j));
							productDetailArray.add(productDetailMap);
						}
						break;
					}
					
				}
				productMap.put("bizApiKey", loginDto.getBizApiKey());
				productMap.put("userId", "root");
				productMap.put("pkUserSeq", loginDto.getPkUserSeq());
				productService.insertProduct(productMap);
				System.out.println("returnId====="+productMap.get("returnId"));
		        if(productDetailArray != null) {
		        	for (HashMap<String, Object> hashMap2 : productDetailArray) {
		        		hashMap2.put("pkProductSeq", productMap.get("returnId"));
		        		hashMap2.put("pkUserSeq", loginDto.getPkUserSeq());
						productService.insertProductDetail(hashMap2);
					}
		        }
			}
			
		return "redirect:productMain";
	}
}
