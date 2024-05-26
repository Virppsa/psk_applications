package com.applications.system.service;

import java.util.concurrent.CompletableFuture;

public interface RequestScopedCompanyService {

    CompletableFuture<Void> performAsyncOperationWithEntityManager(int companyId, String description);
}
