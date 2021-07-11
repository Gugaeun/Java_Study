package com.megait.study210529;

import assembler.Assembler;
import exception.DuplicateMemberDaoException;
import exception.MemberNotFoundException;
import exception.WrongIdPasswordException;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main2 {
    public static void main(String[] args) {
        // DI(Dependency Injection) 의존적 주입
        // DI는 의존하는 객체를 직접 생성하는 대신 의존 객체를 전달받는 방식으로 사용
        // Dependency -> 객체 간의 의존
        // 회원가입 예시

        // 콘솔에서 명령어를 입력받고 각 명령어에 알맞은 기능을 수행하는 코드
        // 처리할 명령어 : 1. new -> 새로운 회원 데이터를 추가  2. change 회원데이터와 암호를 변경
        //               3. exit -> 프로그램 종료

        // 콘솔에서 입력받기 위해 System.in 을 이용해서 BufferedReader 생성
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("명령어를 입력하세요.");
            // 한 줄 입력
            try {
                String command = reader.readLine();

                // 입력한 문자열이 "exit"이면 프로그램 종료
                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("종료합니다.");
                    break;
                }

                // 입력한 문자열이 "new" 로 시작하면 회원가입
                if(command.startsWith("new")) {
                    processNewCommand(command.split(" "));
                }

                // 입력한 문자열이 "change "로 시작하면 processChangeCommand() 메서드를 찾아 실행
                if(command.startsWith("change")) {
                    processChangeCommand(command.split(" "));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
//        MemberDao memberDao = new MemberDao();
//        MemberRegisterService regSvc = new MemberRegisterService(memberDao);
//        ChangePasswordService pwdSvc = new ChangePasswordService();
//        pwdSvc.setMemberDao(memberDao);
    }

    // Assembler 객체가 생성되면서
    // 필요한 객체를 생성하고 의존을 주입한다.
    private static Assembler assembler = new Assembler();


    // 새로운 회원 정보 가입 메소드
    private static void processNewCommand(String[] arg) {
        if(arg.length != 5) {
//            System.out.println("회원정보를 제대로 입력하세요");
            printHelp();
            return;
        }

        // Assembler 객체 사용
        MemberRegisterService regSvc = assembler.getRegSvc();
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        // 입력한 암호 값이 올바른지 확인
        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.\n");
            return;
        }
        // 이미 동일한 이메일을 가진 회원 데이터가 존재하면 에러 메시지 출력
        try {
            regSvc.register(req);

            System.out.println("등록했습니다.\n");
        } catch (DuplicateMemberDaoException e) {
            System.out.println("이미 존재하는 이메일입니다.\n");
        }
    }

    // 새로운 회원 정보 수정 메소드
    private static void processChangeCommand(String[] arg) {
        if (arg.length != 4) {
            printHelp();
            return;
        }

        // Assembler 객체 사용
        ChangePasswordService changePasswordService =
                            assembler.getChangePasswordService();
        try {
            changePasswordService.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호를 변경했습니다.\n");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.\n");
        } catch (WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
        }
    }
    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법:");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println();
    }
}
