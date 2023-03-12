package project.isseyo.product.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.isseyo.login.dto.LoginDto;
import project.isseyo.product.dto.ProductDto;
import project.isseyo.product.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	@GetMapping("/productMain")
    public ModelAndView productMain() {
        
		List<ProductDto> productList = productService.selectProductList();
		System.out.println(productList);
		ModelAndView mv = new ModelAndView("pages/product/main/productMain"); 
		mv.addObject("resultList", productList);
		
		return mv;
    }
	
	@GetMapping("/productForm/{pkProductSeq}")
    public ModelAndView productForm(
    		@PathVariable int pkProductSeq
    		, HttpServletRequest request
    		, Model model
    		) {
		ModelAndView mv = new ModelAndView("pages/product/form/productForm"); 
		
		if(pkProductSeq == 0) {
			mv.addObject("divn", "insert");
		}else {
			request.getSession().getAttribute("loginDto");
			ProductDto productDto = new ProductDto();
			LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
			productDto.setPkProductSeq(pkProductSeq);
			productDto.setPkUserSeq(loginDto.getPkUserSeq());
			
			List<ProductDto> productDetailList = productService.selectProductDetailList(productDto);
			productDto = productService.selectProduct(productDto);
			System.out.println(productService.selectProduct(productDto));
			System.out.println(productDetailList);
			mv.addObject("result", productDto);
			mv.addObject("resultList", productDetailList);
			mv.addObject("divn", "update");
		}
		
		return mv;
    }
	
	@GetMapping("/productDelete/{pkProductSeq}")
    public String productDelete(
    		@PathVariable int pkProductSeq
    		, HttpServletRequest request
    		, Model model
    		) {
		
		productService.deleteProduct(pkProductSeq);
		productService.deleteProductDetail(pkProductSeq);
		
		return "redirect:/productMain";
    }
	
	/**
	 * 품목을 등록 한다.
	 * @param productDto
	 * @param divn
	 * @return "redirect:/productMain"
	 * @exception Exception
	 */
	@PostMapping("/productCreate/{divn}")
	public String productCreate(
			ProductDto productDto
			, @PathVariable String divn
			, HttpServletRequest request
			, Model model
		)
			throws Exception {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		String[] attribute = productDto.getAttribute().split(",");
		String[] value =  productDto.getValue().split(",");
		
		HashMap<String, Object> productMap = new HashMap<String, Object>();
		productMap.put("productName", productDto.getProductName());
		productMap.put("productCode", productDto.getProductCode());
		productMap.put("standard", productDto.getStandard());
		productMap.put("unit", productDto.getUnit());
		productMap.put("productImg", productDto.getProductImg());
		productMap.put("divn", productDto.getDivn());
		productMap.put("etc", productDto.getEtc());
		productMap.put("bizApiKey", loginDto.getBizApiKey());
		productMap.put("pkUserSeq", loginDto.getPkUserSeq());
		
		if(divn.equals("insert")) {
			
			productMap.put("registId", loginDto.getUserId());
			productService.insertProduct(productMap);
			
			System.out.println("productMap.get(\"returnId\")================"+productMap.get("returnId"));
			for (int i = 0; i < value.length; i++) {
				HashMap<String, Object> productDetailMap = new HashMap<String, Object>();
				
				productDetailMap.put("pkProductSeq", productMap.get("returnId"));
				productDetailMap.put("attribute", attribute[i]);
				productDetailMap.put("value", value[i]);
				productDetailMap.put("pkUserSeq", loginDto.getPkUserSeq());
				
				productService.insertProductDetail(productDetailMap);
			}
		} else {
			
			productMap.put("pkProductSeq", productDto.getPkProductSeq());
			productMap.put("updateId", loginDto.getUserId());
			
			productService.updateProduct(productMap);
			productService.deleteProductDetail(productDto.getPkProductSeq());
			
			for (int i = 0; i < value.length; i++) {
				HashMap<String, Object> productDetailMap = new HashMap<String, Object>();
				
				productDetailMap.put("pkProductSeq", productDto.getPkProductSeq());
				productDetailMap.put("attribute", attribute[i]);
				productDetailMap.put("value", value[i]);
				productDetailMap.put("pkUserSeq", loginDto.getPkUserSeq());
				
				productService.insertProductDetail(productDetailMap);
			}
		}
		return "redirect:/productMain";
	}
	
	
}
