package springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exceptions.DuplicateMemberDaoException;
import springmvc.model.RegisterRequest;
import springmvc.service.MemberRegisterService;
import springmvc.utils.RegisterRequestValidator;

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
	// Errors객체를 생성해서 유효성 체크
	public String handleStep3(RegisterRequest regReq, Errors errors) {
		new RegisterRequestValidator().validate(regReq, errors);
		
		if(errors.hasErrors())
			return "register/step2";
	
		try {
			long retMemberNum = memberRegisterService.regist(regReq);
			//model.addAttribute("memberNum", retMemberNum);
		} catch(DuplicateMemberDaoException ex) {
			errors.rejectValue("email", "중복된 이메일이 있습니다. 다른 이메일을 넣어주세요");
			return "register/step2";
		}
		
		return "register/step3";
	}
}
