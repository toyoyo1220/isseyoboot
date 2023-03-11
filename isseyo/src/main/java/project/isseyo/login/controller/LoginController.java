package project.isseyo.login.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
		
	    @GetMapping("/sign")
	    public String signMain() {
	        return "pages/sign/sign";
	    }

	    @PostMapping("/signUp")
	    public String signup(
	    		@ModelAttribute("loginDto") LoginDto loginDto
	    		) {
	    	UUID uuid = UUID.randomUUID();
	    	loginDto.setBizApiKey(uuid.toString());
	    	loginService.insertUser(loginDto);
	        return "redirect:/loginMain";
	    }

}
