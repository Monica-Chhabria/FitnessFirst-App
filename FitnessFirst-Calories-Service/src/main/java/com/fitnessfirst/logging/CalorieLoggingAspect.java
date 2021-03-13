package com.fitnessfirst.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalorieLoggingAspect {
	 // setup pointcut declaration
    @Pointcut("execution(* com.fitnessfirst.Controller.*.*(..))")
    private void forControllerPackage() {
        
    }
    // do the same for service  and dao 
    @Pointcut("execution(* com.fitnessfirst.service.*.*(..))")
    private void forServicePackage() {
        
    }  
    @Pointcut("execution(* com.fitnessfirst.dao.*.*(..))")
    private void forDaoPackage() {
        
    }  
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}
    
    //add @Before advice  
    @Before("forAppFlow()")
    public void before(JoinPoint theJointPoint)
    {
        //diaplay method we are calling
        String theMethod = theJointPoint.getSignature().toShortString();
        CalorieLogger.LogMsg("====> in @Before: calling method " + theMethod);
        
        //display the arguments to the method
        
        
        //GET THE ARGUMENTS
        Object[] args=theJointPoint.getArgs();
        
        //loop through and display args
        for(Object tempArg : args)
        {
        	 CalorieLogger.LogMsg("===> arguments "+ tempArg);
        }
    }
    
    // add @AfterReturing Advice
    
    @AfterReturning(pointcut="forAppFlow()",  
            returning="theResult")
    public void afterReturing(JoinPoint theJointPoint, Object theResult) {
        
        //display method we RE RETURING FROM
        String theMethod = theJointPoint.getSignature().toShortString();
        CalorieLogger.LogMsg("====> in @AfterReturing: from return method " + theMethod);
        
        //DISPLAY DATA RECIEVED
        
        CalorieLogger.LogMsg("===> result : "+theResult);
    }   
}
