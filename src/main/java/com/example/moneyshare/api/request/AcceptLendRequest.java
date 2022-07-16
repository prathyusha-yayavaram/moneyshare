package com.example.moneyshare.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AcceptLendRequest {

    private String lentId;
    private String borrowId;
}
