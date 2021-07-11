package springmvc.model;

import java.time.LocalDateTime;

import exceptions.WrongIdPasswordException;

/*
 * 회원 정보 Entity(DB)
 */
public class Member {
    private Long id;                          // 회원번호(회사가 부여)
    private String email;                     // 회원이 가지는 이메일(고객이 입력)
    private String password;                  // 비밀번호(고객입력)
    private String name;                      // 회원이름(고객입력)
    private LocalDateTime registerDatetime;   // 가입날짜(회사가 부여)

    public Member(String email
	            , String password
	            , String name
	            , LocalDateTime registerDatetime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDatetime = registerDatetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegisterDatetime() {
        return registerDatetime;
    }

    public void setRegisterDatetime(LocalDateTime registerDatetime) {
        this.registerDatetime = registerDatetime;
    }

    // 암호 변경 기능을 구현
    public void changePassword(String oldPassword, String newPassword) {
        if(!password.equals(oldPassword))
            throw new WrongIdPasswordException();

        this.password = newPassword;
    }
}
