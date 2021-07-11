package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mvcstudy1.HelloSpringController;
import springmvc.controller.BoardController;
import springmvc.controller.ChangePwdController;
import springmvc.controller.LoginController;
import springmvc.controller.LogoutController;
import springmvc.controller.MemberRegisterController;
import springmvc.service.AuthService;
import springmvc.service.BoardService;
import springmvc.service.MemberRegisterService;

@Configuration
public class ControllerConfig {

	@Autowired
	private MemberRegisterService memberRegSvc;
	@Autowired
	private AuthService authService;
	@Autowired
	private BoardService boardService;
	
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
	
	@Bean
	public LoginController loginController() {
		LoginController loginController = new LoginController();
		loginController.setAuthService(authService);
		
		return loginController;
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public BoardController boardController() {
		BoardController boardController = new BoardController();
		boardController.setBoardService(boardService);
		
		return boardController;
	}
	
	@Bean
	public ChangePwdController changePwdController() {
		ChangePwdController changePwdController = new ChangePwdController();
//		changePwdController
		
		return changePwdController;
	}
}
