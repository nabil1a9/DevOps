package tn.esprit.kaddemproject.aspects;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class PerformanceAspect {


	@Around("execution(* tn.esprit.kaddemproject.generic.IGenericServiceImp.add(..))")
	public Object executionTime(ProceedingJoinPoint pjp) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
//	    List<Object> obj = new ArrayList<Object>(); // si le type de retour est une liste
//		obj = (List<Object>) pjp.proceed();
		Object obj = pjp.proceed();

		stopWatch.stop();

		String methodName = pjp.getSignature().getName();

		log.info("The runtime of the method ( "+ methodName + " ) = " + stopWatch.getTotalTimeMillis() + " milliseconds.");

		return obj;
	}
//	
	//@Around("execution(* tn.esprit.kaddemproject.generic.IGenericServiceImp.retrieveAll(..))")
	public List<Object> executionTime2(ProceedingJoinPoint pjp) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		List<Object> result = new ArrayList<Object>();
		result=	(List<Object>) pjp.proceed();

		stopWatch.stop();

		String methodName = pjp.getSignature().getName();

		log.info("The runtime of the method ( {} ) =  {}  milliseconds.",methodName,stopWatch.getTotalTimeMillis());

		return result;
	}


}