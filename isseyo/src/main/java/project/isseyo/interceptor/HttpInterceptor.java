package project.isseyo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.isseyo.login.dto.LoginDto;

@Component
public class HttpInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
    	if(loginDto == null) {
    		response.sendRedirect("/");
    	}else {
    	}
    	System.out.println("loginDto====="+loginDto);
    	System.out.println("preHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {
    	System.out.println("postHandle");
    }
}
