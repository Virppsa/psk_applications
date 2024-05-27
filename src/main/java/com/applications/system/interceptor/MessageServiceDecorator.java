package com.applications.system.interceptor;

import com.applications.system.service.glassBox.DefaultMessageServiceImpl;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("decoratorEnabled")
public class MessageServiceDecorator {

    private final DefaultMessageServiceImpl messageService;


    //Dekoratoriai
    public MessageServiceDecorator(DefaultMessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    //Dekoratorius klauso kada bus įvykdytas message request
    @Before("execution(* com.applications.system.service.glassBox.DefaultMessageServiceImpl.getMessage(..))")
    public void decorate() {
        System.out.println("Decorator is applied --------------------------------------------");
    }

    @After("execution(* com.applications.system.service.glassBox.DefaultMessageServiceImpl.getMessage(..))")
    public void decorateAfter() {
        System.out.println("Decorator is applied after --------------------------------------------");
    }
}