package springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springmvc.model.RegisterRequest;
import springmvc.service.MemberRegisterService;

@Controller
@RequestMapping("/register")
public class MemberRegisterController {
	
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
//	@GetMapping("/register/step1")
	@GetMapping("/step1")
	public String handleStep1(HttpServletRequest request) {
//		String test1 = request.getParameter("test1");
//		if(test1 == null || !test1.equals("true"))
//			return "no_found";
				
		return "register/step1";
	}
	
//	@PostMapping("/register/step2")
	@PostMapping("/step2")
	public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree
						      , Model model)
	{
		if(!agree) {
			return "register/step1";
		}
		
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}

	@PostMapping("/step3")
	public String handleStep3(RegisterRequest regReq, Model model) {
		// 실제 Map메모리에 사용자가 입력한 회원가입 정보 저장
		long retMemberNum = memberRegisterService.regist(regReq);
		model.addAttribute("memberNum", retMemberNum);
		
		return "register/step3";
	}
}
