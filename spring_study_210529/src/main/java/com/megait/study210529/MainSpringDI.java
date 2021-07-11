package com.megait.study210529;

import config.*;
import exception.DuplicateMemberDaoException;
import exception.MemberNotFoundException;
import exception.WrongIdPasswordException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainSpringDI {

    private static AnnotationConfigApplicationContext ctx = null;

    public static void main(String[] args) {
//        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
//        ctx = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);
//        ctx = new AnnotationConfigApplicationContext(AppConfImport.class);
        ctx = new AnnotationConfigApplicationContext(AppConfImport2.class);

        // 콘솔에서 입력받기 위해 System.in 을 이용해서 BufferedReader 생성
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        while (true) {
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
                // 사용예: new test1@abc.com 홍길동 1234 1234
                if (command.startsWith("new ")) {
                    processNewCommand(command.split(" "));
                    continue;
                }

                // 입력한 문자열이 "change "로 시작하면 processChangeCommand() 메서드를 찾아 실행하여 회원정보 수정
                // 사용예: change test1@abc.com 1234 2345
                if (command.startsWith("change ")) {
                    processChangeCommand(command.split(" "));
                    continue;
                }

                // 그동안 가입한 회원정보들의 리스트 출력하기
                // 사용예: list
                if (command.equals("list")) {
                    processListCommand();
                    continue;
                }

                // 가입한 회원정보 단 건 출력하기
                // 사용예: info test1@abc.com
                if (command.startsWith("info ")) {
                    processInfoCommand(command.split(" "));
                    continue;
                }

                // 프로그램 버전 출력하기
                // 사용예: version
                if (command.equals("version")) {
                    processVersionCommand();
                    continue;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void processVersionCommand() {
        VersionPrinter versionPrinter =
                ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }

    private static void processInfoCommand(String[] arg) {
        if(arg.length != 2) {       // 명령어를 잘못 입력할 경우
            printHelp();
            return;
        }
        MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        infoPrinter.printMemberInfo(arg[1]);
    }

    // 새로운 회원 정보 가입 메소드
    private static void processNewCommand(String[] arg) {
        if(arg.length != 5) {
//            System.out.println("회원정보를 제대로 입력하세요");
            printHelp();
            return;
        }

        MemberRegisterService regSvc =
                ctx.getBean("memberRegisterService", MemberRegisterService.class);

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

        ChangePasswordService changePasswordService
                = ctx.getBean("changePasswordService", ChangePasswordService.class);

        try {
            changePasswordService.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호를 변경했습니다.\n");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.\n");
        } catch (WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
        }
    }

    private static void processListCommand() {
        // 스프링 컨테이너에서 "listPrinter" 빈 객체를 구한다.
        //  이 빈 객체는 생성자를 통해서 MemberDao 객체와 MemberPrinter 객체를 주입 다는다.
        MemberListPrinter listPrinter =
                ctx.getBean("listPrinter", MemberListPrinter.class);

        listPrinter.printAll();
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
