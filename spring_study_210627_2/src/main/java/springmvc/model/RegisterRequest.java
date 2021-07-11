package springmvc.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import exceptions.WrongIdPasswordException;

public class RegisterRequest {
	@NotBlank
	@Email
	private String email;                     // 회원이 가지는 이메일(고객이 입력)
	@Size(min = 6)
    private String password;                  // 비밀번호(고객입력)
	@NotEmpty
    private String confirmPassword;           // 확인용 비밀번호
	@NotEmpty
    private String name;                      // 회원이름(고객입력)

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPasswordEqualToConfirmPassword() {
        return password.equals(confirmPassword);
    }

    // 암호 변경 기능을 구현
    public void changePassword(String oldPassword, String newPassword) {
        if(!password.equals(oldPassword))
            throw new WrongIdPasswordException();

        this.password = newPassword;
    }
}
