package project.isseyo.product.controller;

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
	
	/**
	 * 품목을 등록 한다.
	 * @param productVO - 등록할 정보가 담긴 VO
	 * @param status
	 * @return "redirect:/productMain"
	 * @exception Exception
	 */
	@PostMapping("/productCreate")
	public String productCreate(
			ProductDto productDto
			, Model model
		)
			throws Exception {

		productService.productCreate(productDto);
		
		return "redirect:/productMain";
	}
	
	
}
