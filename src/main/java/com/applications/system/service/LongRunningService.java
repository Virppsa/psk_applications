package com.applications.system.service;

import java.util.concurrent.CompletableFuture;

public interface LongRunningService {
    CompletableFuture<String> performLongComputation();
}
