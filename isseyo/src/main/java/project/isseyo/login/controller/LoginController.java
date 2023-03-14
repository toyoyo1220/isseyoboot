package project.isseyo.login.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.isseyo.login.dto.LoginDto;
import project.isseyo.login.service.LoginService;
import project.isseyo.order.dto.OrderDto;
import project.isseyo.order.service.OrderService;
import project.isseyo.pay.dto.PayDto;
import project.isseyo.pay.service.PayService;
import project.isseyo.product.dto.ProductDto;
import project.isseyo.product.service.ProductService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private PayService payService;
	/**
	 * 로그인화면 으로 이동 한다.
	 * @param pkProductSeq
	 * @return ModelAndView
	 * @exception Exception
	 */
	@GetMapping("/")
	public String loginMain() {
		return "pages/login/loginMain";
	}
	/**
	 * 로그인화면 으로 이동 한다.
	 * @param pkProductSeq
	 * @return ModelAndView
	 * @exception Exception
	 */
	@GetMapping("/loginMain")
	public String loginView() {
		return "pages/login/loginMain";
	}
	/**
	 * 로그아웃 한다.
	 * @return redirect:/loginMain
	 * @exception Exception
	 */
	@GetMapping("/logout")
	public String loginOut(HttpServletRequest request) {
		request.getSession().setAttribute("loginDto", null);
		return "redirect:/loginMain";
	}
	/**
	 * 메인화면으로 이동 한다.
	 * @return pages/main/main
	 * @exception Exception
	 */
	@GetMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		OrderDto orderDto = new OrderDto();
		PayDto payDto = new PayDto();
		ProductDto productDto = new ProductDto();
		orderDto.setPkUserSeq(loginDto.getPkUserSeq());
		payDto.setPkUserSeq(loginDto.getPkUserSeq());
		productDto.setPkUserSeq(loginDto.getPkUserSeq());
		int orderCount = orderService.seletOrderCount(orderDto);
		int payCount = payService.seletPayCount(payDto);
		int productCount = productService.seletProductCount(productDto);

		List<OrderDto> orderList = orderService.selectOrderListJoin(orderDto);
		System.out.println(orderList);
		
		payDto.setPkUserSeq(loginDto.getPkUserSeq());
		List<PayDto> payList = payService.selectPayListJoin(payDto);
		System.out.println(payList);
		
		model.addAttribute("payList", payList);
		model.addAttribute("orderList", orderList);
		model.addAttribute("productCount", productCount);
		model.addAttribute("orderCount", orderCount);
		model.addAttribute("payCount", payCount);
		return "pages/main/main";
	}
	/**
	 * 로그인 한다.
	 * @param loginDto
	 * @return ModelAndView
	 * @exception Exception
	 */
	@PostMapping("/loginUp")
	public String loginUp(@ModelAttribute("loginDto") LoginDto loginDto, HttpServletRequest request) {
		LoginDto loginCheck = loginService.selectUser(loginDto);
		System.out.println(loginCheck);
		if (loginCheck != null) {
			request.getSession().setAttribute("loginDto", loginCheck);
			return "redirect:main";
		} else {
			return "redirect:/loginMain";
		}
	}
	/**
	 * 회원가입 등록/수정 화면으로 이동 한다.
	 * @param divn
	 * @return ModelAndView
	 * @exception Exception
	 */
	@GetMapping("/sign/{divn}")
	public ModelAndView signMain(@PathVariable String divn, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("pages/sign/sign");

		if (divn.equals("update")) {
			LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
			mv.addObject("result", loginDto);
		} else {
		}
		mv.addObject("divn", divn);
		return mv;
	}
	/**
	 * 회원가입 정보를 등록한다
	 * @return loginMain
	 * @exception Exception
	 */
	@PostMapping("/signUp/{divn}")
	public String signup(@ModelAttribute("loginDto") LoginDto loginDto, @PathVariable String divn) {
		if (divn.equals("update")) {
			loginService.updateUser(loginDto);
		} else {
			UUID uuid = UUID.randomUUID();
			loginDto.setBizApiKey(uuid.toString());
			loginService.insertUser(loginDto);
		}

		return "redirect:/loginMain";
	}

}
