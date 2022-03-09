package com.example.moneyshare.repository;

import com.example.moneyshare.domain.User;
import com.example.moneyshare.firebase.AbstractFirestoreRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractFirestoreRepository<User> {
    protected UserRepository() {
        super(FirestoreClient.getFirestore(), "usersCollection");
    }
}