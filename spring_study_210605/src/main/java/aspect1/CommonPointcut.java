package aspect1;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {
    @Pointcut("execution(public * module..*(..))")
    public void commonTarget() {
    }
}
