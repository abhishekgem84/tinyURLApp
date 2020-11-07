package com.tiny.url.app.controller.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j(topic = "org.request.response.logging")
public class LoggingHandler {
    @Autowired(required = false)
    private HttpServletRequest request;

    @Pointcut("execution(* com.tiny.url.app.rest.service.*.requestTo*(..))")
    public void pointCutMethod() {
    }

    /*
     * @AfterReturning(pointcut =
     * "pointCutMethod() && args(..,@RequestBody requestBody)", returning =
     * "result") public void logAfter(JoinPoint joinPoint, Object result, Object
     * requestBody) { // String returnValue = this.getValue(result); ObjectMapper
     * objectMapper = new ObjectMapper(); try { log.info("--REQUEST-- " +
     * objectMapper.writeValueAsString(requestBody) + " --RESPONSE-- " +
     * objectMapper.writeValueAsString(result));
     * 
     * } catch (Exception e) { log.error(e.toString()); } }
     */

    @Around("pointCutMethod() && args(..,@RequestBody requestBody)")
    public Object logAround(ProceedingJoinPoint joinPoint, Object requestBody) throws Throwable {

        long start = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("Method " + className + "." + methodName + " ()" + " execution time : " + elapsedTime + " ms"
                    + " --REQUEST-- " + objectMapper.writeValueAsString(requestBody) + " --RESPONSE-- "
                    + objectMapper.writeValueAsString(result) + " --URL-- " + request.getRequestURL()
                    + " --HTTP Method-- " + request.getMethod());

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }
}
