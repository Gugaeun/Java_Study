package main;

import config.AppCtxAOP1;
import module.Calculator;
import module.CalculatorImpl;
import module.CalculatorRecImpl;
import module.NanotimeCalculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAOP {
    public static void main(String[] args) {
        Calculator calculator1 = new CalculatorImpl();
        long startTime = System.currentTimeMillis();
        calculator1.factorial(10000);
        long endTime = System.currentTimeMillis();
        System.out.printf("CalculatorImpl.factorial(3) 실행 시간 = %d\n", (endTime - startTime));

        Calculator calculator2 = new CalculatorRecImpl();
        startTime = System.currentTimeMillis();
        calculator2.factorial(10000);
        endTime = System.currentTimeMillis();
        System.out.printf("CalculatorRecImpl.factorial(3) 실행 시간 = %d\n", (endTime - startTime));

        // AOP구현
        // Calculator의 factorial() 기능 자체를 직접 구현하기보다는 NanotimeCalulator 객체에 factorial()메소드 실행을 위임
        // NanotimeCalculator는 사실 계산 기능 외에 다른 부가적인 기능(여기서는 시간을 측정)을 실행
        // 이렇게 핵심 기능의 실행은 다른 객체에 위임하고 부가적인 기능을 제공하는 객체를 프록시(proxy)라고 부름.
        // 공통 기능 구현과 핵심 기능 구현을 분리하는 것이 AOP의 핵심.
        // AOP는 Aspect Oriented Programming의 약자로서
        // 여러 객체에 공통으로 적용할 수 있는 기능을 분리해서 재사용성을 높여주는 프로그래밍 기법이다.
        NanotimeCalculator calculator3 = new NanotimeCalculator(new CalculatorImpl());
        long result = calculator3.factorial(1000);

        NanotimeCalculator calculator4 = new NanotimeCalculator(new CalculatorRecImpl());
        result = calculator4.factorial(1000);

        // 핵심 기능에 공통 기능을 삽입하는 방법
        // (1) 컴파일 시점에 코드에 공통 기능을 삽입
        // (2) 클래스 로딩 시점에 바이트 코드에 공통 기능을 삽입
        // (3) 런타임 시점에 프록시 객체를 생성해서 공통 기능을 삽입

        // AOP 주요 용어
        // (1) Advice : 언제 공통 관심 기능을 핵심 로직에 적용할 지를 정의하고 있다.
        // (2) Joinpoint : Advice를 적용 가능한 지점을 의미한다. 메서드 호출, 필드 값 변경 등.
        //                 스프링은 메서드 호출에 대한 Joinpoint만 지원한다.
        // (3) Pointcut : Joinpoint의 부분 집합으로서 실제 Advice가 적용되는 Joinpoint를 나타낸다.
        // (4) Weaving : Advice를 핵심 로직 코드에 적용하는 것
        // (5) Aspect : 여러 객체에 공통으로 적용되는 기능

        // 스프링은 프록시를 이용해서 메서드 호출 시점에 Aspect를 적용.
//         - Before Advice	: 대상 객체의 메서드 호출 전에 공통 기능을 실행
//         - After Returning Advice : 대상 객체의 메서드가 익셉션 없이 실행된 이후에 공통 기능을 실행
//         - After Throwing Advice : 대상 객체의 메서드를 실행하는 도중 익셉션이 발생한 경우에 공통 기능을 실행
//         - After Advice : 익셉션 발생 여부에 상관없이 메서드 실행 후 공통 기능 실행
//         - Around Advice : 메서드 실행 전, 후 또는 익셉션 발생 시점에 공통 기능 실행
//        이중에 널리 사용되는 것은 Around Advice 이다.
//        왜냐하면 대상 객체의 메서드를 실행 하기 전/후, 익셉션 발생 시점 등 다양한 시점에 원하는 기능을 삽입 가능

        System.out.println("==========================================");
        // 스프링 AOP 구현
        // @Aspect, @Pointcut, @Around를 이용하여 AOP 구현 실습
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxAOP1.class);

        // 자바 코드, "calculator1" 프록시의 실제 타입은 RecCalculator를 상속받았으므로
        // CalculatorRecImpl로 타입 변환 가능 -> @EnableAspectJAutoProxy(proxyTargetClass = true)
//        Calculator cal1 = ctx.getBean("calculator1", Calculator.class);
        CalculatorRecImpl cal1 = ctx.getBean("calculator1", CalculatorRecImpl.class);
        long factorialResult1 = cal1.factorial(1000);
        System.out.println("cal1.factorial(1000) = " + factorialResult1);
        System.out.println(cal1.getClass().getName());

        Calculator cal2 = ctx.getBean("calculator2", Calculator.class);
        long factorialResult2 = cal2.factorial(1000);
        System.out.println("cal2.factorial(1000) = " + factorialResult2);
        System.out.println(cal2.getClass().getName());

        ctx.close();
    }
}
