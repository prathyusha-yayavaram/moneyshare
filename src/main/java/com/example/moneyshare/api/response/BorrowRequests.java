package com.example.moneyshare.api.response;

import com.example.moneyshare.entity.BorrowDetails;
import com.example.moneyshare.entity.LentDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BorrowRequests {
    private List<BorrowDetails> borrowDetailsList;
}
