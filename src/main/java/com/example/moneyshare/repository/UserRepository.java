package com.example.moneyshare.repository;

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

    public User getDecrypted(String id) throws Exception {
        Optional<User> borrowDetailsOptional = get(id);
        User user = borrowDetailsOptional.get();
        Encrypter encrypter = new Encrypter();
        user.setName(encrypter.decrypt(user.getName()));
        user.setSsn(encrypter.decrypt(user.getSsn()));
        return user;
    }

    public Timestamp saveEncrypted(User user) throws Exception {
        Encrypter encrypter = new Encrypter();
        user.setName(encrypter.encrypt(user.getName()));
        user.setSsn(encrypter.encrypt(user.getSsn()));
        return save(user);
    }
}