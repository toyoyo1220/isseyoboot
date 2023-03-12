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
import project.isseyo.error.ErrorHandler;
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
	public ArrayList<HashMap<String, Object>> selectProduct(
			@PathVariable String bizApiKey
			, @PathVariable int pkProductSeq
			) {
		
			LoginDto loginDto = null;
			ProductDto productDto = new ProductDto();
			ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> reponseMap = new HashMap<String, Object>();
			
		try {
			System.out.println("bizApiKey======="+bizApiKey);
			loginDto = loginService.apiCheack(bizApiKey);
			System.out.println("loginDto======="+loginDto);
			productDto.setPkUserSeq(loginDto.getPkUserSeq());
			productDto.setPkProductSeq(pkProductSeq);
			productDto = productService.selectProduct(productDto);
			System.out.println(productDto);
			
			reponseMap.put("result", productDto);
			reponseMap.put("message", "조회 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
		} catch (Exception e) {
				// TODO: handle exception
				response = ErrorHandler.serverError("500", "회원 정보가 존재하지 않습니다.");
		}
		
		
		return response;
	}
	
	@GetMapping(value = "get/product/all/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> selectProductList(
			@PathVariable String bizApiKey
			) {
		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		try {
		
			System.out.println("bizApiKey======="+bizApiKey);
			loginDto = loginService.apiCheack(bizApiKey);
			productDto.setPkUserSeq(loginDto.getPkUserSeq());
			List<ProductDto> resultList = productService.selectProductList(productDto);
			
			reponseMap.put("result", resultList);
			reponseMap.put("message", "조회 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}

	@PostMapping(value = "post/product/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> productInsert(
			@PathVariable String bizApiKey
			, @RequestBody ArrayList<HashMap<String, Object>> resultList
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		try {
			System.out.println("bizApiKey======="+bizApiKey);
			loginDto = loginService.apiCheack(bizApiKey);
		
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
		reponseMap.put("result", resultList);
		reponseMap.put("message", "삽입 성공");
		reponseMap.put("state", "200");
		
		response.add(reponseMap);
		
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}
	
	@PutMapping(value = "put/product/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> productUpdate(
			@PathVariable String bizApiKey
			, @RequestBody ArrayList<HashMap<String, Object>> resultList
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		try {
			loginDto = loginService.apiCheack(bizApiKey);
			System.out.println("bizApiKey======="+bizApiKey);
			
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
			reponseMap.put("result", resultList);
			reponseMap.put("message", "수정 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}
	
	@DeleteMapping(value = "delete/product/{bizApiKey}/{pkProductSeq}")
	public ArrayList<HashMap<String, Object>> productDelete(
			@PathVariable String bizApiKey
			, @PathVariable int pkProductSeq
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		try {
			loginDto = loginService.apiCheack(bizApiKey);
			System.out.println("bizApiKey======="+bizApiKey);
			productService.deleteProduct(pkProductSeq);
			productService.deleteProductDetail(pkProductSeq);
		
			reponseMap.put("result", "");
			reponseMap.put("message", "삭제 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}
	
	@GetMapping(value = "get/order/{bizApiKey}/{pkOrderSeq}")
	public ArrayList<HashMap<String, Object>> selectOrder(
			@PathVariable String bizApiKey
			, @PathVariable int pkOrderSeq
			) {
		
			LoginDto loginDto = null;
			ProductDto productDto = new ProductDto();
			ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> reponseMap = new HashMap<String, Object>();
			
		try {
			System.out.println("bizApiKey======="+bizApiKey);
			loginDto = loginService.apiCheack(bizApiKey);
			System.out.println("loginDto======="+loginDto);
			productDto.setPkUserSeq(loginDto.getPkUserSeq());
			productDto.setPkProductSeq(pkOrderSeq);
			productDto = productService.selectProduct(productDto);
			System.out.println(productDto);
			
			reponseMap.put("result", productDto);
			reponseMap.put("message", "조회 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
		} catch (Exception e) {
				// TODO: handle exception
				response = ErrorHandler.serverError("500", "회원 정보가 존재하지 않습니다.");
		}
		
		
		return response;
	}
	
	@GetMapping(value = "get/order/all/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> selectOrderList(
			@PathVariable String bizApiKey
			) {
		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		try {
		
			System.out.println("bizApiKey======="+bizApiKey);
			loginDto = loginService.apiCheack(bizApiKey);
			productDto.setPkUserSeq(loginDto.getPkUserSeq());
			List<ProductDto> resultList = productService.selectProductList(productDto);
			
			reponseMap.put("result", resultList);
			reponseMap.put("message", "조회 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}

	@PostMapping(value = "post/order/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> orderInsert(
			@PathVariable String bizApiKey
			, @RequestBody ArrayList<HashMap<String, Object>> resultList
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		try {
			System.out.println("bizApiKey======="+bizApiKey);
			loginDto = loginService.apiCheack(bizApiKey);
		
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
		reponseMap.put("result", resultList);
		reponseMap.put("message", "삽입 성공");
		reponseMap.put("state", "200");
		
		response.add(reponseMap);
		
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}
	
	@PutMapping(value = "put/order/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> orderUpdate(
			@PathVariable String bizApiKey
			, @RequestBody ArrayList<HashMap<String, Object>> resultList
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		try {
			loginDto = loginService.apiCheack(bizApiKey);
			System.out.println("bizApiKey======="+bizApiKey);
			
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
			reponseMap.put("result", resultList);
			reponseMap.put("message", "수정 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}
	
	@DeleteMapping(value = "delete/order/{bizApiKey}/{pkProductSeq}")
	public ArrayList<HashMap<String, Object>> orderDelete(
			@PathVariable String bizApiKey
			, @PathVariable int pkProductSeq
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		try {
			loginDto = loginService.apiCheack(bizApiKey);
			System.out.println("bizApiKey======="+bizApiKey);
			productService.deleteProduct(pkProductSeq);
			productService.deleteProductDetail(pkProductSeq);
		
			reponseMap.put("result", "");
			reponseMap.put("message", "삭제 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}
	@GetMapping(value = "get/pay/{bizApiKey}/{pkProductSeq}")
	public ArrayList<HashMap<String, Object>> selectPay(
			@PathVariable String bizApiKey
			, @PathVariable int pkProductSeq
			) {
		
			LoginDto loginDto = null;
			ProductDto productDto = new ProductDto();
			ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> reponseMap = new HashMap<String, Object>();
			
		try {
			System.out.println("bizApiKey======="+bizApiKey);
			loginDto = loginService.apiCheack(bizApiKey);
			System.out.println("loginDto======="+loginDto);
			productDto.setPkUserSeq(loginDto.getPkUserSeq());
			productDto.setPkProductSeq(pkProductSeq);
			productDto = productService.selectProduct(productDto);
			System.out.println(productDto);
			
			reponseMap.put("result", productDto);
			reponseMap.put("message", "조회 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
		} catch (Exception e) {
				// TODO: handle exception
				response = ErrorHandler.serverError("500", "회원 정보가 존재하지 않습니다.");
		}
		
		
		return response;
	}
	
	@GetMapping(value = "get/pay/all/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> selectPayList(
			@PathVariable String bizApiKey
			) {
		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		try {
		
			System.out.println("bizApiKey======="+bizApiKey);
			loginDto = loginService.apiCheack(bizApiKey);
			productDto.setPkUserSeq(loginDto.getPkUserSeq());
			List<ProductDto> resultList = productService.selectProductList(productDto);
			
			reponseMap.put("result", resultList);
			reponseMap.put("message", "조회 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}

	@PostMapping(value = "post/pay/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> payInsert(
			@PathVariable String bizApiKey
			, @RequestBody ArrayList<HashMap<String, Object>> resultList
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		try {
			System.out.println("bizApiKey======="+bizApiKey);
			loginDto = loginService.apiCheack(bizApiKey);
		
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
		reponseMap.put("result", resultList);
		reponseMap.put("message", "삽입 성공");
		reponseMap.put("state", "200");
		
		response.add(reponseMap);
		
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}
	
	@PutMapping(value = "put/pay/{bizApiKey}")
	public ArrayList<HashMap<String, Object>> payUpdate(
			@PathVariable String bizApiKey
			, @RequestBody ArrayList<HashMap<String, Object>> resultList
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		try {
			loginDto = loginService.apiCheack(bizApiKey);
			System.out.println("bizApiKey======="+bizApiKey);
			
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
			reponseMap.put("result", resultList);
			reponseMap.put("message", "수정 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}
	
	@DeleteMapping(value = "delete/pay/{bizApiKey}/{pkProductSeq}")
	public ArrayList<HashMap<String, Object>> payDelete(
			@PathVariable String bizApiKey
			, @PathVariable int pkProductSeq
			, HttpServletRequest req) {

		LoginDto loginDto = null;
		ProductDto productDto = new ProductDto();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		try {
			loginDto = loginService.apiCheack(bizApiKey);
			System.out.println("bizApiKey======="+bizApiKey);
			productService.deleteProduct(pkProductSeq);
			productService.deleteProductDetail(pkProductSeq);
		
			reponseMap.put("result", "");
			reponseMap.put("message", "삭제 성공");
			reponseMap.put("state", "200");
			
			response.add(reponseMap);
		} catch (Exception e) {
			// TODO: handle exception
			response = ErrorHandler.serverError("500", e.getMessage());
		}
		return response;
	}
}
