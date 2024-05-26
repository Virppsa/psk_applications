package com.applications.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service

public class LongRunningServiceImpl implements LongRunningService {
    @Async
    public CompletableFuture<String> performLongComputation() {
        try {
            // Simuliuota komputacija su Thread.sleep
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.completedFuture("Interrupted");
        }
        return CompletableFuture.completedFuture("Some computation result");
    }
}
