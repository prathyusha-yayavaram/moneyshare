package com.example.moneyshare.service;

import com.example.moneyshare.domain.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@DependsOn("firebaseInitialization")
public class UserService {
    public String COLLECTION_NAME = "usersCollection";
    public Firestore dbFireStore = FirestoreClient.getFirestore();

    public String saveUserDetails(User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> apiFuture = dbFireStore.collection(COLLECTION_NAME).document(user.getName()).set(user);
        return apiFuture.get().getUpdateTime().toString();
    }
}
