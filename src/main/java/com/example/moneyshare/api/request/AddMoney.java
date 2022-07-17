package com.example.moneyshare.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddMoney {
    private String id;
    private Long amount;
}
