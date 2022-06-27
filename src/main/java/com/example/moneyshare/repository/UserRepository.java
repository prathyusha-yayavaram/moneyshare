package com.example.moneyshare.repository;

import com.example.moneyshare.entity.User;
import com.example.moneyshare.firebase.AbstractFirestoreRepository;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractFirestoreRepository<User> {
    protected UserRepository() {
        super(FirestoreClient.getFirestore(), "usersCollection");
    }
}