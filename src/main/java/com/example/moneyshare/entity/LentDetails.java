package com.example.moneyshare.entity;

import com.example.moneyshare.model.BorrowRequests;
import com.example.moneyshare.model.LentStatus;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gcp.data.firestore.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document(collectionName = "lentDetails")
@Getter
@Setter
public class LentDetails {

    @DocumentId
    private String id;

    private String userId;
    private String creditScore;
    private String amount;
    private String roi;
    private LentStatus status;
    private List<String> borrowLists;

}
