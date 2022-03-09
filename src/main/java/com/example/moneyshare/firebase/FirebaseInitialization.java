package com.example.moneyshare.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitialization {

    @PostConstruct
    public void initialization() {
        InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("./serviceAccountKey.json");
        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://moneyshare-f6562-default-rtdb.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseApp.initializeApp(options);
    }
}
