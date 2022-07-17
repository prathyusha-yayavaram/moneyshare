package com.example.moneyshare.controller;

import com.example.moneyshare.api.request.AcceptLendRequest;
import com.example.moneyshare.api.request.AddMoney;
import com.example.moneyshare.api.request.BorrowRequest;
import com.example.moneyshare.api.request.LendRequest;
import com.example.moneyshare.api.response.*;
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

    @PostMapping(path = "/addMoney", consumes = "application/json", produces = "application/json")
    public Response addMoney(@RequestBody AddMoney addMoney) {
        return userService.addMoney(addMoney);
    }

    @GetMapping(path = "/getWalletAmount")
    public WalletResponse getWalletAmount(@RequestParam String id) {
        return userService.getWalletAmount(id);
    }

    @PostMapping(path = "/addLendRequest", consumes = "application/json", produces = "application/json")
    public Response addLentRequest(@RequestBody LendRequest lentRequest) {
        return lentService.addLentRequest(lentRequest);
    }

    @PostMapping(path = "/addBorrowRequest", consumes = "application/json", produces = "application/json")
    public Response addBorrowRequest(@RequestBody BorrowRequest borrowRequest) {
        return lentService.addBorrowRequest(borrowRequest);
    }

    @GetMapping(path = "/getLentRequests")
    public LentRequests getLentRecords(@RequestParam String id) {
        return lentService.getLentRecords(id);
    }

    @GetMapping(path = "/getBorrowRequests")
    public BorrowRequests getBorrowRequest(@RequestParam String id) {
        return lentService.getBorrowRecords(id);
    }

    @PostMapping(path = "/acceptLentRequest", consumes = "application/json", produces = "application/json")
    public String acceptLentRequest(@RequestBody AcceptLendRequest acceptLendRequest) {
        return lentService.acceptLendRequest(acceptLendRequest.getLentId(), acceptLendRequest.getBorrowId());
    }
}
