package com.example.moneyshare.service;

import com.example.moneyshare.api.request.BorrowRequest;
import com.example.moneyshare.api.request.LendRequest;
import com.example.moneyshare.api.response.BorrowRequests;
import com.example.moneyshare.api.response.LentRequests;
import com.example.moneyshare.api.response.Response;
import com.example.moneyshare.entity.BorrowDetails;
import com.example.moneyshare.entity.LentDetails;
import com.example.moneyshare.entity.User;
import com.example.moneyshare.model.BorrowStatus;
import com.example.moneyshare.model.LentStatus;
import com.example.moneyshare.repository.BorrowDetailsRepository;
import com.example.moneyshare.repository.LentDetailsRepository;
import com.example.moneyshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@DependsOn("firebaseInitialization")
public class LendService {

    @Autowired
    private BorrowDetailsRepository borrowDetailsRepository;

    @Autowired
    private LentDetailsRepository lentDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    public LentRequests getLentRecords(String userId) {
        List<LentDetails> lentDetailsList = lentDetailsRepository.retrieveAll().stream().filter(x -> userId.equals(x.getUserId())).collect(Collectors.toList());
        for (LentDetails lentDetails : lentDetailsList) {
            if(!LentStatus.PENDING.equals(lentDetails.getStatus())) continue;
            List<BorrowDetails> borrowDetailsList = borrowDetailsRepository.retrieveAll().stream().filter(x -> x.getAmount().equals(lentDetails.getAmount())
                    && x.getRoi().compareTo(lentDetails.getRoi()) >= 0 && x.getCreditScore().compareTo(lentDetails.getCreditScore()) >= 0 && BorrowStatus.PENDING.equals(x.getStatus())).collect(Collectors.toList());
            lentDetails.setBorrowLists(borrowDetailsList);
        }
        LentRequests lentRequests = new LentRequests();
        lentRequests.setLentDetailsList(lentDetailsList);
        return lentRequests;
    }

    public BorrowRequests getBorrowRecords(String userId) {
        List<BorrowDetails> borrowDetailsList = borrowDetailsRepository.retrieveAll().stream().filter(x -> userId.equals(x.getUserId())).collect(Collectors.toList());
        BorrowRequests borrowRequests = new BorrowRequests();
        borrowRequests.setBorrowDetailsList(borrowDetailsList);
        return borrowRequests;
    }

    public Response addLentRequest(LendRequest request) {
        LentDetails lentDetails = new LentDetails();
        lentDetails.setAmount(request.getAmount());
        lentDetails.setUserId(request.getUserId());
        lentDetails.setCreditScore(request.getMinCreditScore());
        lentDetails.setRoi(request.getRoi());
        lentDetails.setStatus(LentStatus.PENDING);
        lentDetails.setId(request.getUserId() + "-l");
        lentDetailsRepository.save(lentDetails);
        User user = userRepository.get(request.getUserId()).get();
        if(user.getWalletAmount() == null) {
            user.setWalletAmount(0L);
        }
        if(user.getLentAmount() == null) {
            user.setLentAmount(0L);
        }
        user.setWalletAmount(user.getWalletAmount() - request.getAmount());
        user.setLentAmount(user.getLentAmount() + request.getAmount());
        userRepository.save(user);
        Response response = new Response();
        response.setMessage("OK");
        return response;
    }

    public Response addBorrowRequest(BorrowRequest request) {
        BorrowDetails borrowDetails = new BorrowDetails();
        borrowDetails.setAmount(request.getAmount());
        borrowDetails.setUserId(request.getUserId());
        User user = userRepository.get(request.getUserId()).get();
        borrowDetails.setCreditScore(user.getCreditScore());
        borrowDetails.setRoi(request.getRoi());
        borrowDetails.setStatus(BorrowStatus.PENDING);
        borrowDetails.setId(request.getUserId() + "-b");
        borrowDetailsRepository.save(borrowDetails);
        Response response = new Response();
        response.setMessage("OK");
        return response;
    }

    public Response acceptLendRequest(String lendId, String borrowId) {
        LentDetails lentDetails = lentDetailsRepository.get(lendId).get();
        BorrowDetails borrowDetails = borrowDetailsRepository.get(borrowId).get();
        borrowDetails.setStatus(BorrowStatus.EXECUTED);
        borrowDetails.setLentId(lentDetails.getId());
        borrowDetailsRepository.save(borrowDetails);
        User user = userRepository.get(borrowDetails.getUserId()).get();
        if(user.getBorrowAmount() == null) {
            user.setBorrowAmount(0L);
        }
        user.setBorrowAmount(user.getBorrowAmount() + borrowDetails.getAmount());
        if(user.getWalletAmount() == null) {
            user.setWalletAmount(0L);
        }
        user.setWalletAmount(user.getWalletAmount() + borrowDetails.getAmount());
        userRepository.save(user);
        List<BorrowDetails> borrowDetailsList = new ArrayList<>();
        borrowDetailsList.add(borrowDetails);
        lentDetails.setBorrowLists(borrowDetailsList);
        lentDetails.setStatus(LentStatus.EXECUTED);
        lentDetailsRepository.save(lentDetails);
        Response response = new Response();
        response.setMessage("OK");
        return response;
    }

    public Response settleBorrowRequest(String borrowId) {
        BorrowDetails borrowDetails = borrowDetailsRepository.get(borrowId).get();
        LentDetails lentDetails = lentDetailsRepository.get(borrowDetails.getLentId()).get();
        borrowDetails.setStatus(BorrowStatus.COMPLETED);
        borrowDetailsRepository.save(borrowDetails);
        User user = userRepository.get(borrowDetails.getUserId()).get();
        if(user.getBorrowAmount() == null) {
            user.setBorrowAmount(0L);
        }
        user.setBorrowAmount(user.getBorrowAmount() - borrowDetails.getAmount());
        if(user.getWalletAmount() == null) {
            user.setWalletAmount(0L);
        }
        user.setWalletAmount(user.getWalletAmount() - borrowDetails.getAmount());
        userRepository.save(user);
        lentDetails.setStatus(LentStatus.COMPLETED);
        lentDetailsRepository.save(lentDetails);
        User lentUser = userRepository.get(lentDetails.getUserId()).get();
        if(user.getWalletAmount() == null) {
            user.setWalletAmount(0L);
        }
        if(user.getLentAmount() == null) {
            user.setLentAmount(0L);
        }
        lentUser.setWalletAmount(lentUser.getWalletAmount() + borrowDetails.getAmount());
        lentUser.setLentAmount(lentUser.getLentAmount() - borrowDetails.getAmount());
        userRepository.save(lentUser);
        Response response = new Response();
        response.setMessage("OK");
        return response;
    }

}
