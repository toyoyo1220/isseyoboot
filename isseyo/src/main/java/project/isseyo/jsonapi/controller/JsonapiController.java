package project.isseyo.jsonapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import project.isseyo.login.dto.LoginDto;
import project.isseyo.product.dto.ProductDto;
import project.isseyo.product.service.ProductService;

@RestController
@RequestMapping(value = "json/api")
public class JsonapiController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "get/{bizApiKey}")
	public String selectAllBookInfo(
			@PathVariable String bizApiKey
			) {
		System.out.println("bizApiKey======="+bizApiKey);
		ProductDto productDto = new ProductDto();
		productDto.setBizApiKey("1");
		productDto.setPkProductSeq(106);
		// 사용자 ID를 사용하여 사용자 정보를 검색하고 반환
		productDto = productService.selectProduct(productDto);
		return "성공";
	}

	@PostMapping(value = "/post/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> createUser(@PathVariable String bizApiKey,
			@RequestBody ArrayList<HashMap<String, Object>> resultList, HttpServletRequest req) {

		LoginDto loginDto = null;
		// loginDto = loginService.apiCheack(bizApiKey);
		/* if(loginVO != null) { */
		for (HashMap<String, Object> hashMap : resultList) {

			int returnId = productService.insertProduct(hashMap);
			ArrayList<HashMap<String, Object>> data = null;

			data = (ArrayList<HashMap<String, Object>>) hashMap.get("data");
			if (data != null) {
				for (HashMap<String, Object> hashMap2 : data) {
					hashMap2.put("pkProductSeq", returnId);
					hashMap2.put("pkUserSeq", 11);
					productService.insertProductDetail(hashMap2);
				}
			}
		}
		/* } */
		return resultList;
	}
}
