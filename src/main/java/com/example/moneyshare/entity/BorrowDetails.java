package com.example.moneyshare.entity;

import com.example.moneyshare.firebase.DocumentId;
import com.example.moneyshare.model.BorrowStatus;
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
    private Long amount;
    private Double creditScore;
    private Double roi;
    private BorrowStatus status;
    private String lentId;

}
