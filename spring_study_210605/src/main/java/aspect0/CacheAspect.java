package aspect0;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
//@Order(2)         // Aspect 실행 순서 세팅(번호가 높을수록 먼저 실행)
public class CacheAspect {
    private Map<Long, Object> cache = new HashMap<>();

//    @Pointcut("execution(public * module..*(long))")
    public void cacheTarget() {}

//    @Around("cacheTarget()")
//    @Around("execution(public * module..*(long))")
    @Around("aspect0.ExeTimeAspect.publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        // 첫번째 인자를 Long타입으로 세팅(-> factorial(num)의 num값)
        Long num = (Long)joinPoint.getArgs()[0];

        // num값의 key가 cache에 존재하면 키에 해당하는 값을 구해서 리턴
        if(cache.containsKey(num)) {
            System.out.printf("CacheAspect: Cache에서 구함[%d]\n", num);
            return cache.get(num);
        }

        // 처음에 구한 key값이 cache에 존재하지 않으면 프록시(proxy) 대상 객체(factorial메소드)를 실행한다.
        Object result = joinPoint.proceed();
        // 프록시 대상 객체를 실행한 결과를 cache에 추가
        cache.put(num, result);
        System.out.printf("CacheAspect: Cache에 추가[%d]\n", num);
        // 프록시 대상 객체의 실행 결과를 리턴한다.
        return result;
    }
}
