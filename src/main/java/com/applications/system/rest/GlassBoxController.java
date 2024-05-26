package com.applications.system.rest;

import com.applications.system.service.glassBox.AlternativeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/glassbox")
public class GlassBoxController {
    private AlternativeMessageService messageService;

    public GlassBoxController(AlternativeMessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message")
    public String greet() {
        return messageService.getMessage();
    }
}
