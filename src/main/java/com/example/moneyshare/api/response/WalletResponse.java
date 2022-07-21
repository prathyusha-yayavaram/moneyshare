package com.example.moneyshare.api.response;

import com.example.moneyshare.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WalletResponse {
    private Long totalAmount;
    private Long lentAmount;
    private Long borrowedAmount;
    private User user;
}
