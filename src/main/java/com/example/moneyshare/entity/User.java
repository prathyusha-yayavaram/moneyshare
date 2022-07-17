package com.example.moneyshare.entity;

import com.example.moneyshare.firebase.DocumentId;
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

    private Double creditScore;

    private Long walletAmount;

    private Long lentAmount;

    private Long borrowAmount;

}