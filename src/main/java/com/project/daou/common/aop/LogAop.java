package com.project.daou.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LogAop {

    private final static Logger logger = LoggerFactory.getLogger(LogAop.class);

    //controller 이하 모든 패키지의 모든 클래스 이하 모든 메서드에 적용
    @Pointcut("execution(* com.project.daou.controller..*.*(..))")
    private void cut() {
    }

    @Around("cut()")
    public Object beforeParameterLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = new Object() {
        }.getClass().getEnclosingClass().getName();

        StopWatch stopWatch = new StopWatch();
        logger.info(className + "stopWatch.start() ===========");
        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();
        logger.info(className + "stopWatch.stop() ============");
        System.out.println("[LOG] 처리시간: " + (stopWatch.getTotalTimeMillis() / 1000.0) + "초");
        return proceed;

    }

}
