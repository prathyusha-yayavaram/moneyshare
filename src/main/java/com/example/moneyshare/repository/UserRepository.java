package com.example.moneyshare.repository;

import com.example.moneyshare.encryption.Decrypter;
import com.example.moneyshare.encryption.Encrypter;
import com.example.moneyshare.entity.User;
import com.example.moneyshare.firebase.AbstractFirestoreRepository;
import com.google.cloud.Timestamp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends AbstractFirestoreRepository<User> {
    protected UserRepository() {
        super(FirestoreClient.getFirestore(), "usersCollection");
    }

    public User getDecrypted(String id) {
        Optional<User> borrowDetailsOptional = get(id);
        User user = borrowDetailsOptional.get();
        user.setName(Decrypter.decrypt(user.getName()));
        user.setSsn(Decrypter.decrypt(user.getSsn()));
        return user;
    }

    public Timestamp saveEncrypted(User user) {
        user.setName(Encrypter.encrypt(user.getName()));
        user.setSsn(Encrypter.encrypt(user.getSsn()));
        return save(user);
    }
}