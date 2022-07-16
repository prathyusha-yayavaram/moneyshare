package com.example.moneyshare.repository;

import com.example.moneyshare.entity.LentDetails;
import com.example.moneyshare.firebase.AbstractFirestoreRepository;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

@Repository
public class LentDetailsRepository extends AbstractFirestoreRepository<LentDetails> {
    protected LentDetailsRepository() {
        super(FirestoreClient.getFirestore(), "lentDetails");
    }
}
