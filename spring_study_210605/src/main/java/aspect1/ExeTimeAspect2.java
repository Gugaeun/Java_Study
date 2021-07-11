package aspect1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class ExeTimeAspect2 {
    @Around("aspect1.CommonPointcut.commonTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        try {
            return joinPoint.proceed();     // factorial실행
        } finally {
            long endTime = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns \n"
                    , joinPoint.getTarget().getClass().getSimpleName()
                    , sig.getName(), Arrays.toString(joinPoint.getArgs())
                    , endTime - startTime
            );
        }
    }
}
