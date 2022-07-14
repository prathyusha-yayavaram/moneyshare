package com.example.moneyshare.repository;

import com.example.moneyshare.entity.LentDetails;
import com.example.moneyshare.firebase.AbstractFirestoreRepository;
import com.google.firebase.cloud.FirestoreClient;

public class BorrowDetailsRepository  extends AbstractFirestoreRepository<LentDetails> {
    protected BorrowDetailsRepository() {
        super(FirestoreClient.getFirestore(), "borrowDetails");
    }
}
