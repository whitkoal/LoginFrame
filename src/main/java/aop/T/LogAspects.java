package aop.T;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspects {

    @Pointcut("execution(public int aop.T.MathCalculator.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+"除法开始...@Befor...参数表： "+ Arrays.asList(args));
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getArgs()+"日志打印结束...");
    }

    @AfterReturning("pointCut()")
    public void logReturn() {
        System.out.println("除法正常返回...");
    }

    @AfterThrowing("pointCut()")
    public void logException() {
        System.out.println("除法异常...异常信息： {}");
    }
}
