package com.example.moneyshare.controller;

import com.example.moneyshare.api.request.LentRequest;
import com.example.moneyshare.api.response.LentResponse;
import com.example.moneyshare.api.response.WalletResponse;
import com.example.moneyshare.entity.User;
import com.example.moneyshare.service.LentService;
import com.example.moneyshare.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class MoneyShareController {

    @Autowired
    private UserService userService;

    @Autowired
    private LentService lentService;

    @GetMapping(path = "/healthCheck")
    public String checkHealth() {
        return "OK\n";
    }

    @GetMapping(path = "/isNewUser")
    public Boolean isNewUser(@RequestParam String id) {
        return userService.isNewUser(id);
    }

    @PostMapping(path = "/saveUser", consumes = "application/json", produces = "application/json")
    public String saveUserDetails(@RequestBody User user) {
        return userService.saveUserDetails(user);
    }

    @GetMapping(path = "/getWalletAmount")
    public WalletResponse getWalletAmount(@RequestParam String id) {
        return userService.getWalletAmount(id);
    }

    @PostMapping(path = "/lentRequest")
    public LentResponse getLentRecords(@RequestParam String id) {
        return lentService.getLentRecords(id);
    }
}
