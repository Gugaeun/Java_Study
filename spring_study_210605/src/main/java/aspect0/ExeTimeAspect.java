package aspect0;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Aspect
//@Order(1)     // Aspect 실행 순서 세팅(번호가 높을수록 먼저 실행)
public class ExeTimeAspect{

    /**
     * execution 명시자 표현식
     * 기본 형식 : execution(수식어패턴 리턴타입패턴 클래스이름패턴 메서드이름패턴(파라미터패턴))
     * 수식어 패턴 : public
     * 리턴타입패턴 : 리턴 타입을 명시
     * 클래스이름패턴/메서드이름패턴 : 클래스 이름 및 메서드 이름을 명시
     * 파라미터패턴 : 매칭될 파라미터에 대해서 명시
     * -> 각 패턴은 '*'을 이용해서 모든 값을 표현 가능
     * '..'을 이용하여 0개 이상이라는 의미를 표현 가능
     * ---execution 명시자 예시---
     *  ex) - execution(public void set*(..)) -> 리턴 타입 void, 메서드 이름은 set으로 시작, 파라미터 : 0개 이상
     *      - execution(* module.*.*()) -> module패키지의 타입에 속한 파라미터가 없는 모든 메소드 호출
     *      - execution(* module..*.*(..)) -> module패키지 및 하위 패키지에 있는, 파라미터가 0개 이상인 메소드 호출
     *      - execution(Long module .Calculator.factorial(..)) -> 리턴 타입이 Long인 Calculator타입의 factorial()메소드 호출
     *      - execution(* get*(*)) -> 이름이 get으로 시작하고 파라미터가 1개인 메소드 호출
     *      - execution(* get*(*,*)) -> 이름이 get으로 시작하고 파라미터가 2개인 메소드 호출
     *      - execution(* read*(Integer, ..)) -> 이름이 read으로 시작하고 첫번째 파라미터 타입이 Integer이며, 한 개 이상의 파라미터를 갖는 메소드 호출
     */
    @Pointcut("execution(public * module..*(..))")
    public void publicTarget() {
        System.out.println("ExeTimeAspect.publicTarget() 실행");
    }

    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        try {
            return joinPoint.proceed();     // factorial실행
        } finally {
            long endTime = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            /**
                org.aspect.lang.Signature 인터페이스가 제공하는 메서드
                String getName() : 호출되는 메서드의 이름을 구한다.
                String toLongString() : 호출되는 메서드를 완전하게 표현한 문장을 구한다.
                String toShortString() : 호출되는 메서드를 축약해서 표현한 문장을 구한다.
             */
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns \n"
                    , joinPoint.getTarget().getClass().getSimpleName()
                    , sig.getName(), Arrays.toString(joinPoint.getArgs())
                    , endTime - startTime
            );
        }
    }
}
