package aspect1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class CacheAspect2 {
    private Map<Long, Object> cache = new HashMap<>();

    @Around("aspect1.CommonPointcut.commonTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        Long num = (Long)joinPoint.getArgs()[0];

        if(cache.containsKey(num)) {
            System.out.printf("CacheAspect2: Cache에서 구함[%d]\n", num);
            return cache.get(num);
        }

        Object result = joinPoint.proceed();
        cache.put(num, result);
        System.out.printf("CacheAspect2: Cache에 추가[%d]\n", num);
        return result;
    }
}
