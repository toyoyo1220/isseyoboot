package project.isseyo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import project.isseyo.interceptor.HttpInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new HttpInterceptor())
	                .addPathPatterns("/*")
	        		.excludePathPatterns("/logout")
	        		.excludePathPatterns("/")
	        		.excludePathPatterns("/loginMain"); // 해당 경로는 인터셉터가 가로채지 않는다.
	    }
}
