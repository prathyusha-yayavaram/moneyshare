package com.example.moneyshare.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WalletResponse {
    private Long totalAmount;
    private Long lentAmount;
    private Long borrowedAmount;
}
