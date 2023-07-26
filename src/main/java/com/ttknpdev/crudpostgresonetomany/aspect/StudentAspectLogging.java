package com.ttknpdev.crudpostgresonetomany.aspect;

import com.ttknpdev.crudpostgresonetomany.entities.Student;
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
public class StudentAspectLogging extends Logging {
    @Before(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoStudent.read(..))")
    private void loggingBeforeRead(JoinPoint joinPoint) {
        aspectStudent.log(Level.INFO,"*** Logging Before read(..) method get call ***");
        aspectStudent.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ Arrays.toString(joinPoint.getArgs())+" }");
    }
    @Before(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoStudent.create(..))")
    private void loggingBeforeCreate(JoinPoint joinPoint) {
        aspectStudent.log(Level.INFO,"*** Logging Before create(..) method post call ***");
        aspectStudent.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ Arrays.toString(joinPoint.getArgs())+" }");
        // return pojo before insert to db so any auto increment will be null
    }
    @Before(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoStudent.delete(..))")
    private void loggingBeforeDelete(JoinPoint joinPoint) {
        aspectStudent.log(Level.INFO,"*** Logging Before delete(..) method post call ***");
        aspectStudent.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ Arrays.toString(joinPoint.getArgs())+" }");
    }
/*    @Before(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoStudent.update(..))")
    private void loggingBeforeUpdate(JoinPoint joinPoint) {
        aspectStudent.log(Level.INFO,"*** Logging Before update(..) method put call ***");
        aspectStudent.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ Arrays.toString(joinPoint.getArgs())+" }");
    }*/
    @AfterReturning(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoStudent.update(..))", returning = "student") // don't forget if you have args in method for giving aspect ** just add (..) == args
    private void loggingAfterUpdate(JoinPoint joinPoint , Map<String,Student> student) {
        aspectStudent.log(Level.INFO,"*** Logging After update(..) method put call ***");
        aspectStudent.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ student+" }");
    }
    @AfterReturning(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoStudent.create(..))", returning = "student") // don't forget if you have args in method for giving aspect ** just add (..) == args
    private void loggingAfterCreate(JoinPoint joinPoint , Student student) {
        aspectStudent.log(Level.INFO,"*** Logging After create(..) method post call ***");
        aspectStudent.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ student+" }");
        // return pojo after insert to db so will get auto increment id
    }
    @AfterReturning(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoStudent.read(..))", returning = "student") // don't forget if you have args in method for giving aspect ** just add (..) == args
    private void loggingAfterRead(JoinPoint joinPoint , Student student) {
        aspectStudent.log(Level.INFO,"*** Logging After read(..) method get call ***");
        aspectStudent.log(Level.INFO,"Enter: { "+joinPoint.getSignature().getDeclaringTypeName()+
                " } , Method: { "+ joinPoint.getSignature().getName()+
                " } , Return: { "+ student+" }");
    }
    @AfterReturning(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoStudent.reads())", returning = "students")
    private void loggingAfterReads(JoinPoint joinPoint , List<Student> students){
        aspectStudent.log(Level.INFO,"*** Logging After reads() method was done ***");
        aspectStudent.log(Level.INFO,"Enter: {"+joinPoint.getSignature().getDeclaringTypeName()+
                "} , Method: { "+ joinPoint.getSignature().getName()+
                "} , Return: { "+students+" }");
    }

    @AfterReturning(value = "execution(* com.ttknpdev.crudpostgresonetomany.daos.DaoStudent.delete(..))", returning = "student")
    private void loggingAfterDelete(JoinPoint joinPoint , Map<String,Student> student){
        aspectStudent.log(Level.INFO,"*** Logging After delete() method was done ***");
        aspectStudent.log(Level.INFO,"Enter: {"+joinPoint.getSignature().getDeclaringTypeName()+
                "} , Method: { "+ joinPoint.getSignature().getName()+
                "} , Return: { "+student+" }");
    }
}
