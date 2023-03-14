package project.isseyo.api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.isseyo.login.dto.LoginDto;
import project.isseyo.product.dto.ProductDto;

@Controller
public class ApiSampleController {
	
	@GetMapping("/apiMain")
    public String apiMain(HttpServletRequest request) {
		
		return "pages/api/apiSample";
    }
	
}
