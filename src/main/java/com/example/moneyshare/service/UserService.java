package com.example.moneyshare.service;

import com.example.moneyshare.api.request.AddMoney;
import com.example.moneyshare.api.response.IsNewUser;
import com.example.moneyshare.api.response.Response;
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

    public Response saveUserDetails(User user) {
        Response response = new Response();
        response.setMessage(userRepository.save(user).toString());
        return response;
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

    public Response addMoney(AddMoney addMoney) {
        String id = addMoney.getId();
        User user = userRepository.get(id).get();
        Long existingAmount = user.getWalletAmount() == null ? 0 : user.getWalletAmount();
        user.setWalletAmount(existingAmount + addMoney.getAmount());
        userRepository.save(user);
        Response response = new Response();
        response.setMessage("OK");
        return response;
    }
}
