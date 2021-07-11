package mvcstudy1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloSpringController {
	
	// GetMapping : 메서드가 처리할 요청 경로를 지정
	// GET 메서드에 매핑을 설정한다.
	@GetMapping("/hello-springmvc")
	public String hello(// Model : 컨트롤러의 처리 결과를 뷰에 전달할 때 사용
						Model model
						// @RequestParam : HTTP 요청 파라미터의 값을 메서드의 
			            //  파라미터로 전달할때 사용
			            //  name 요청의 파라미터의 값을 name 파라미터에 전달
						, @RequestParam(value = "name1", required = false) String name) {
		// "greeting" 이라는 모델 속성에 값을 설정한다.
		model.addAttribute("greeting", "안녕하세요~ " + name);
		
		// "hello-spring" : 컨트롤러의 처리 결과를 보여줄 뷰 이름
		return "hello-spring";
	}
}
