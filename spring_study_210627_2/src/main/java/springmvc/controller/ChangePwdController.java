package springmvc.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springmvc.model.ChangePwdCommand;
import springmvc.model.LoginCommand;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {

	@GetMapping()
	public String form(@ModelAttribute("command") ChangePwdCommand pwdCmd) {
		return "edit/changePwdForm";
	}
	
	@PostMapping()
	public String formSubmit(@Valid @ModelAttribute("command") ChangePwdCommand changePwdCommand
			, Errors errors
			, HttpSession session
	) {
		if(errors.hasErrors())
			return "edit/changePwdForm";
		
		return "edit/changePwd";
	}
			
}
