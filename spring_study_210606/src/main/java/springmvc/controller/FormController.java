package springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/form")
public class FormController {

	@GetMapping
	public String form(@RequestParam int type, Model model) {
		List<String> loginTypes = new ArrayList<String>();
		loginTypes.add("일반회원");
		loginTypes.add("기업회원");
		loginTypes.add("헤드헌터회원");
		model.addAttribute("loginTypes", loginTypes);
		model.addAttribute("type", type);

		if(type == 1) {
			stress1();
		} else {
			noStress1();
		}
		
		return "form/form";
	}
	
	private void stress1() {
		for(int i = 0; i < 3000000; i++) {
			System.out.println("stress1 : " + i);
		}
	}
	
	private void noStress1() {
		for(int i = 0; i < 200; i++) {
			System.out.println("noStress1 : " + i);
		}
	}
}
