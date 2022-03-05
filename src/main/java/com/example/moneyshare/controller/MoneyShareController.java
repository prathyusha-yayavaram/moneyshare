package com.example.moneyshare.controller;

import com.example.moneyshare.domain.User;
import com.example.moneyshare.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/api")
public class MoneyShareController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/saveUser", consumes = "application/json", produces = "application/json")
    public String saveUserDetails(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.saveUserDetails(user);
    }
  /*  private Boolean flag = true;
    @PostMapping(path = "/putUserData", consumes = "application/json", produces = "application/json")
    public Response addEmployee(@RequestBody Request request) {
        log.info("Coupon generated for user " + request.getBulkUsers() + " using the ruleId " + request.getRuleId());
        Response response = new Response();
        CouponDetails couponDetails = new CouponDetails();
        if(flag) {
            couponDetails.setCoupon("MYNPECCHITRUE500");
            flag = false;
        }
        else {
            couponDetails.setCoupon("MYNPECCHIFALSE500");
            flag = true;
        }
        couponDetails.setExpiryDate(1609281026L);
        couponDetails.setConditionMin(1499.0);
        couponDetails.setConditionMax(0.0);
        couponDetails.setCouponAmount(0.0);
        couponDetails.setCouponPercentage(15.0);
        couponDetails.setStatus("A");
        couponDetails.setCouponType("percentage");
        couponDetails.setDescription("15% off on minimum purchase of Rs. 1499.0");
        couponDetails.setApplicable(false);
        Map<String, CouponDetails> data = new HashMap<>();
        String uidx = request.getBulkUsers() != null ? request.getBulkUsers() : "TEST";
        data.put(uidx, couponDetails);
        response.setData(data);
        return response;*/
}
