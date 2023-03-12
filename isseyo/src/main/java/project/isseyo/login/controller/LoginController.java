package project.isseyo.login.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.isseyo.login.dto.LoginDto;
import project.isseyo.login.service.LoginService;

@Controller
public class LoginController {
	 	
		@Autowired
	    private LoginService loginService;
		

		@GetMapping("/")
	    public String loginMain() {
	        return "pages/login/loginMain";
	    }
		
		@GetMapping("/loginMain")
	    public String loginView() {
	        return "pages/login/loginMain";
	    }
		
		@GetMapping("/logout")
	    public String loginOut(HttpServletRequest request) {
			request.getSession().setAttribute("loginDto", null);
			return "redirect:/loginMain";
	    }
		
		@GetMapping("/main")
	    public String main() {
	    	return "pages/main/main";
	    }
		
		@PostMapping("/loginUp")
	    public String loginUp(@ModelAttribute("loginDto") LoginDto loginDto, HttpServletRequest request ) {
			LoginDto loginCheck = loginService.selectUser(loginDto);
			System.out.println(loginCheck);
			if(loginCheck != null) {
				request.getSession().setAttribute("loginDto", loginCheck);
				return "redirect:main";
			}else { 
				return "redirect:/loginMain"; 
			}
	    }
		
	    @GetMapping("/sign/{divn}")
	    public ModelAndView signMain(
	    		@PathVariable String divn
	    		, HttpServletRequest request ) {
	    	ModelAndView mv = new ModelAndView("pages/sign/sign");
	    	
	    	if(divn.equals("update")) {
	    		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
				mv.addObject("result", loginDto);
	    	}else {
	    	}
	    	mv.addObject("divn", divn);
	    	return mv;
	    }

	    @PostMapping("/signUp/{divn}")
	    public String signup(
	    		@ModelAttribute("loginDto") LoginDto loginDto
	    		, @PathVariable String divn
	    		) {
	    	if(divn.equals("update")) {
	    		loginService.updateUser(loginDto);
	    	}else {
	    		UUID uuid = UUID.randomUUID();
		    	loginDto.setBizApiKey(uuid.toString());
		    	loginService.insertUser(loginDto);
	    	}
	    	
	        return "redirect:/loginMain";
	    }

}
