package com.example.moneyshare.service;

import com.example.moneyshare.api.request.LentRequest;
import com.example.moneyshare.api.response.LentResponse;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("firebaseInitialization")
public class LentService {
    public LentResponse getLentRecords(String userId) {

        return null;
    }
}
