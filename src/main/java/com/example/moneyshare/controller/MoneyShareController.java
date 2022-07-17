package com.example.moneyshare.controller;

import com.example.moneyshare.api.request.AcceptLendRequest;
import com.example.moneyshare.api.request.BorrowRequest;
import com.example.moneyshare.api.request.LendRequest;
import com.example.moneyshare.api.response.IsNewUser;
import com.example.moneyshare.api.response.Response;
import com.example.moneyshare.api.response.WalletResponse;
import com.example.moneyshare.entity.BorrowDetails;
import com.example.moneyshare.entity.LentDetails;
import com.example.moneyshare.entity.User;
import com.example.moneyshare.service.BorrowService;
import com.example.moneyshare.service.LendService;
import com.example.moneyshare.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class MoneyShareController {

    @Autowired
    private UserService userService;

    @Autowired
    private LendService lentService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping(path = "/healthCheck")
    public String checkHealth() {
        return "OK\n";
    }

    @GetMapping(path = "/isNewUser")
    public IsNewUser isNewUser(@RequestParam String id) {
        return userService.isNewUser(id);
    }

    @PostMapping(path = "/saveUser", consumes = "application/json", produces = "application/json")
    public Response saveUserDetails(@RequestBody User user) {
        return userService.saveUserDetails(user);
    }

    @GetMapping(path = "/getWalletAmount")
    public WalletResponse getWalletAmount(@RequestParam String id) {
        return userService.getWalletAmount(id);
    }

    @PostMapping(path = "/addLentRequest", consumes = "application/json", produces = "application/json")
    public String addLentRequest(@RequestBody LendRequest lentRequest) {
        return lentService.addLentRequest(lentRequest);
    }

    @PostMapping(path = "/addBorrowRequest", consumes = "application/json", produces = "application/json")
    public String addBorrowRequest(@RequestBody BorrowRequest borrowRequest) {
        return lentService.addBorrowRequest(borrowRequest);
    }

    @PostMapping(path = "/getLentRequest")
    public List<LentDetails> getLentRecords(@RequestParam String id) {
        return lentService.getLentRecords(id);
    }

    @PostMapping(path = "/getBorrowRequest")
    public List<BorrowDetails> getBorrowRequest(@RequestParam String id) {
        return lentService.getBorrowRecords(id);
    }

    @PostMapping(path = "/acceptLentRequest", consumes = "application/json", produces = "application/json")
    public String acceptLentRequest(@RequestBody AcceptLendRequest acceptLendRequest) {
        return lentService.acceptLendRequest(acceptLendRequest.getLentId(), acceptLendRequest.getBorrowId());
    }
}
