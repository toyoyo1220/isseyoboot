package project.isseyo.jsonapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import project.isseyo.login.dto.LoginDto;
import project.isseyo.login.service.LoginService;
import project.isseyo.product.dto.ProductDto;
import project.isseyo.product.service.ProductService;

@RestController
@RequestMapping(value = "json/api")
public class JsonapiController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private LoginService loginService;

	@GetMapping(value = "get/product/{bizApiKey}/{pkProductSeq}")
	public ProductDto selectProduct(
			@PathVariable String bizApiKey
			, @PathVariable int pkProductSeq
			) {
		System.out.println("bizApiKey======="+bizApiKey);
		LoginDto loginDto = null;
		loginDto = loginService.apiCheack(bizApiKey);
		ProductDto productDto = new ProductDto();
		productDto.setPkUserSeq(loginDto.getPkUserSeq());
		productDto.setPkProductSeq(pkProductSeq);
		// 사용자 ID를 사용하여 사용자 정보를 검색하고 반환
		productDto = productService.selectProduct(productDto);
		return productDto;
	}
	
	@GetMapping(value = "get/product/all/{bizApiKey}")
	public List<ProductDto> selectProductList(
			@PathVariable String bizApiKey
			) {
		System.out.println("bizApiKey======="+bizApiKey);
		LoginDto loginDto = null;
		loginDto = loginService.apiCheack(bizApiKey);
		ProductDto productDto = new ProductDto();
		productDto.setPkUserSeq(loginDto.getPkUserSeq());
		// 사용자 ID를 사용하여 사용자 정보를 검색하고 반환
		List<ProductDto> resultList = productService.selectProductList(productDto);
		return resultList;
	}

	@PostMapping(value = "post/product/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> productInsert(
			@PathVariable String bizApiKey
			, @RequestBody ArrayList<HashMap<String, Object>> resultList
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		loginDto = loginService.apiCheack(bizApiKey);
		System.out.println("bizApiKey======="+bizApiKey);
		/* if(loginVO != null) { */
		for (HashMap<String, Object> hashMap : resultList) {
			hashMap.put("pkUserSeq", loginDto.getPkUserSeq());
			hashMap.put("registId", loginDto.getUserId());
			hashMap.put("bizApiKey", loginDto.getBizApiKey());
			productService.insertProduct(hashMap);
			ArrayList<HashMap<String, Object>> data = null;
			data = (ArrayList<HashMap<String, Object>>) hashMap.get("data");
			if (data != null) {
				for (HashMap<String, Object> hashMap2 : data) {
					hashMap2.put("pkProductSeq", hashMap.get("returnId"));
					hashMap2.put("pkUserSeq", loginDto.getPkUserSeq());
					productService.insertProductDetail(hashMap2);
				}
			}
		}
		/* } */
		return resultList;
	}
	
	@PutMapping(value = "put/product/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> productUpdate(
			@PathVariable String bizApiKey
			, @RequestBody ArrayList<HashMap<String, Object>> resultList
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		loginDto = loginService.apiCheack(bizApiKey);
		System.out.println("bizApiKey======="+bizApiKey);
		/* if(loginVO != null) { */
		for (HashMap<String, Object> hashMap : resultList) {
			hashMap.put("pkUserSeq", loginDto.getPkUserSeq());
			hashMap.put("registId", loginDto.getUserId());
			hashMap.put("bizApiKey", loginDto.getBizApiKey());
			productService.updateProduct(hashMap);
			int pkProductSeq = Integer.parseInt((String) hashMap.get("pkProductSeq"));
			productService.deleteProductDetail(pkProductSeq);
			ArrayList<HashMap<String, Object>> data = null;
			data = (ArrayList<HashMap<String, Object>>) hashMap.get("data");
			if (data != null) {
				for (HashMap<String, Object> hashMap2 : data) {
					hashMap2.put("pkProductSeq", hashMap.get("pkProductSeq"));
					hashMap2.put("pkUserSeq", loginDto.getPkUserSeq());
					productService.insertProductDetail(hashMap2);
				}
			}
		}
		/* } */
		return resultList;
	}
	
	@DeleteMapping(value = "delete/product/{bizApiKey}/{pkProductSeq}")
	public String productDelete(
			@PathVariable String bizApiKey
			, @PathVariable int pkProductSeq
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		loginDto = loginService.apiCheack(bizApiKey);
		System.out.println("bizApiKey======="+bizApiKey);
		productService.deleteProduct(pkProductSeq);
		productService.deleteProductDetail(pkProductSeq);
		/* if(loginVO != null) { */
		/* } */
		return "성공";
	}
}
