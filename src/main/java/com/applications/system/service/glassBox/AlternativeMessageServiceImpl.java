package com.applications.system.service.glassBox;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//@Alternative
@Service
//@Profile("scenario2")
//@Profile("!scenario1")
public class AlternativeMessageServiceImpl implements AlternativeMessageService {
    @Override
    public String getMessage(){
        return "Hello from scenario 2";
    }
}