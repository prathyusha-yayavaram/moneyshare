package com.example.moneyshare.domain;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.PropertyName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gcp.data.firestore.Document;

@Document(collectionName = "usersCollection")
@Getter
@Setter
public class User {

    @DocumentId
    private String id;

    private String name;

    private String ssn;

    private String walletAmount;

}