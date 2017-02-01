package com.estsoft.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MeasureDaoExecutionTimeAspect 
{
	@Around("execution(* *..dao.*.*(..)) || execution(* *..service.*.*(..)) || execution(* *..controller.*.*(..)) ")
	public Object around(ProceedingJoinPoint pjp) throws Throwable
	{
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object obj = pjp.proceed();
		
		stopWatch.stop();
		
		String taskName = pjp.getTarget().getClass() + "." + pjp.getSignature().getName(); // 이 메소드가 실행되는 객체의 클래스 이름 + 메소드 이름
		System.out.println("[ExecutionTime][" + taskName + "]:" + stopWatch.getTotalTimeMillis() + "millis");
		
		return obj;
	}
}
