package com.ttknpdev.crudpostgresonetomany.aspect;

import com.ttknpdev.crudpostgresonetomany.entities.Subject;
import com.ttknpdev.crudpostgresonetomany.log.Logging;
import org.apache.log4j.Level;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class SubjectAspectLogging extends Logging {
    @Before("execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoSubject.create(..))")
    private void loggingBeforeCreate(JoinPoint joinPoint) {
        aspectSubject.log(Level.INFO,"*** Logging Before create(..) method post call ***");
        aspectSubject.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ Arrays.toString(joinPoint.getArgs())+" }");
    }
    @Before("execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoSubject.reads(..))")
    private void loggingBeforeReads(JoinPoint joinPoint) {
        aspectSubject.log(Level.INFO,"*** Logging Before reads(..) method post call ***");
        aspectSubject.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ Arrays.toString(joinPoint.getArgs())+" }");
    }
    @Before("execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoSubject.update(..))")
    private void loggingBeforeUpdate(JoinPoint joinPoint) {
        aspectSubject.log(Level.INFO,"*** Logging Before update(..) method post call ***");
        aspectSubject.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ Arrays.toString(joinPoint.getArgs())+" }");
    }
    @AfterReturning(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoSubject.create(..))" ,returning = "subject")
    private void loggingAfterCreate(JoinPoint joinPoint , Subject subject) {
        aspectSubject.log(Level.INFO,"*** Logging After create(..) method post call ***");
        aspectSubject.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ subject+" }");
    }

    @AfterReturning(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoSubject.reads(..))" ,returning = "subjects")
    private void loggingAfterReads(JoinPoint joinPoint , List<Subject> subjects) {
        aspectSubject.log(Level.INFO,"*** Logging After reads(..) method post call ***");
        aspectSubject.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ subjects+" }");
    }
    @AfterReturning(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoSubject.update(..))" ,returning = "subject")
    private void loggingAfterUpdate(JoinPoint joinPoint , Map<String,Subject> subject) {
        aspectSubject.log(Level.INFO,"*** Logging After update(..) method post call ***");
        aspectSubject.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ subject+" }");
    }
    @AfterReturning(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoSubject.delete(..))" ,returning = "subject")
    private void loggingAfterDelete(JoinPoint joinPoint , Map<String,Subject> subject) {
        aspectSubject.log(Level.INFO,"*** Logging After delete(..) method post call ***");
        aspectSubject.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ subject+" }");
    }
}
