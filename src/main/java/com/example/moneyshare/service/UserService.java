package com.example.moneyshare.service;

import com.example.moneyshare.api.response.IsNewUser;
import com.example.moneyshare.api.response.WalletResponse;
import com.example.moneyshare.entity.User;
import com.example.moneyshare.repository.UserRepository;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public WalletResponse getWalletAmount(String id) {
        WalletResponse response = new WalletResponse();
        User user = userRepository.get(id).get();
        response.setTotalAmount(user.getWalletAmount());
        response.setLentAmount(user.getLentAmount());
        response.setBorrowedAmount(user.getBorrowAmount());
        return response;
    }

    //addmoney

    public IsNewUser isNewUser(String id) {
        Optional<User> user = userRepository.get(id);
        IsNewUser isNewUser = new IsNewUser();
        isNewUser.setIsNewUser(!user.isPresent());
        return isNewUser;
    }
}
