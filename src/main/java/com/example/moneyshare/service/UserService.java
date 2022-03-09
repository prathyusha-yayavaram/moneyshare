package com.example.moneyshare.service;

import com.example.moneyshare.domain.User;
import com.example.moneyshare.repository.UserRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@DependsOn("firebaseInitialization")
public class UserService {
    public String COLLECTION_NAME = "usersCollection";
    public Firestore dbFireStore = FirestoreClient.getFirestore();
    public CollectionReference collection = dbFireStore.collection(COLLECTION_NAME);

    @Autowired
    public UserRepository userRepository;

    public String saveUserDetails(User user) {
        return userRepository.save(user).toString();
    }

    public Boolean isNewUser(String id) {
        Optional<User> user = userRepository.get(id);
        return user.isEmpty();
    }
}
