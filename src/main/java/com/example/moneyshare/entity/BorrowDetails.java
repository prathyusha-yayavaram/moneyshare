package com.example.moneyshare.entity;

import com.example.moneyshare.model.BorrowStatus;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gcp.data.firestore.Document;

@Document(collectionName = "borrowDetails")
@Getter
@Setter
public class BorrowDetails {

    @DocumentId
    private String id;

    private String userId;
    private String amount;
    private String creditScore;
    private String roi;

    private BorrowStatus status;

}
