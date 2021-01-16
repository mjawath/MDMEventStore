package com.tech.commons.aop.eventsourcing;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Date;

@Component
@Aspect
//@Order(Integer.MAX_VALUE)
public class EventSource {

    @Autowired
    private EventRepo repo;

    @Before("execution(* com.mycompany.entitybase.service.BaseService.create(*))")
    public Object prePersist(final JoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Before - event sourcing through aop");

        Object value;

        try {
            value = proceedingJoinPoint.getSignature();
            Object[] args = proceedingJoinPoint.getArgs();
            if(TransactionSynchronizationManager.isActualTransactionActive()){
                System.out.println("transaction is active ................");
            }
            TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
            if(transactionStatus!=null){
                System.out.println("transactionStatus");
           }
            Event s = new Event();
            s.setTimestamp(new Date());
            repo.save(s);
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            long duration = System.currentTimeMillis() - start;

        }
        System.out.println("Before END event sourcing through aop Signature: "+value);

        return "value";
    }

    @After("execution(* com.tech.commons.aop.eventsourcing.GenericService.callsomething())")
    public Object persist(final JoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("After event sourcing through aop");

        Object value;

        try {
            value = proceedingJoinPoint.getSignature();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            long duration = System.currentTimeMillis() - start;

        }
        System.out.println("After END event sourcing through aop");

        return "value";
    }

    //within certain transaction
    public void persistEvent(Event event){
        //if transaction is still active
        repo.save(event);
    }



//    @Around("execution(* com.tech.commons.aop.eventsourcing.GenericService.callsomething())")
//    public Object aroundPersist(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//        System.out.println("Around event sourcing through aop");
//
//        Object value = null;
//
//        try {
//            value = proceedingJoinPoint.proceed();
//            System.out.println("signature ..............");
//            System.out.println(value);
//        } catch (Throwable throwable) {
//            throw throwable;
//        } finally {
//            long duration = System.currentTimeMillis() - start;
//
//        }
//        System.out.println("Around END event sourcing through aop");
//
//        return "value";
//    }
}

