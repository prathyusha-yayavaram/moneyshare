package com.example.moneyshare.entity;

import com.google.cloud.firestore.annotation.DocumentId;
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

    private String creditScore;

    private Long walletAmount;

    private Long lentAmount;

    private Long borrowAmount;

}