package com.example.moneyshare.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BorrowRequest {

    private String userId;
    private Long amount;
    private Double roi;

}
