package com.applications.system.service.glassBox;

import com.applications.system.interceptor.LogExecutionTime;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//@Alternative
@Service
@Primary // Vietoje @Primary (nes jo nera spring boote)
//@Profile("scenario1") // Vietoje @Alternative
public class DefaultMessageServiceImpl implements AlternativeMessageService {
    @Override
    @LogExecutionTime //čia naudosime interceptorių
    public String getMessage(){
        return "Hello from scenario 1";
    }

}
