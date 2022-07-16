package com.example.moneyshare.repository;

import com.example.moneyshare.entity.BorrowDetails;
import com.example.moneyshare.firebase.AbstractFirestoreRepository;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

@Repository
public class BorrowDetailsRepository  extends AbstractFirestoreRepository<BorrowDetails> {
    protected BorrowDetailsRepository() {
        super(FirestoreClient.getFirestore(), "borrowDetails");
    }
}
