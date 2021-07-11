package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mvcstudy1.HelloSpringController;
import springmvc.controller.MemberRegisterController;
import springmvc.service.MemberRegisterService;

@Configuration
public class ControllerConfig {

	@Autowired
	private MemberRegisterService memberRegSvc;
	
	@Bean
	public MemberRegisterController memberRegisterController() {
		MemberRegisterController memberRegisterController = new MemberRegisterController();
		memberRegisterController.setMemberRegisterService(memberRegSvc);
		
		return memberRegisterController;
	}
	
	@Bean
	public HelloSpringController helloController() {
		return new HelloSpringController();
	}
}
