package springmvc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import springmvc.model.RegisterRequest;

public class RegisterRequestValidator implements Validator {
	// 이메일 정규표현식
	private static final String emailRegExp = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		pattern = Pattern.compile(emailRegExp);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// clazz 객체가 RegisterRequest 클래스로 타입 변환이 가능한 확인
		// 스프링 MVC가 자동으로 검증 기능을 수행하도록 설정
		return RegisterRequest.class.isAssignableFrom(clazz);
	}

	@Override
	// target : 검사 대상 객체
	// errors : 검사 결과 에러 코드를 설정하기 위한 객체
	public void validate(Object target, Errors errors) {
		// 전달받은 target을 실제 타입으로 변환
		RegisterRequest regReq = (RegisterRequest)target;
		
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			// 정규 표현식을 이용해서 이메일이 올바른지 확인
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) {
				// 정규 표현식이 일치하지 않을 경우
				errors.rejectValue("email", "bad");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}
	
}
