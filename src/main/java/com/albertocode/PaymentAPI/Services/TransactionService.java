package com.albertocode.PaymentAPI.Services;

import com.albertocode.PaymentAPI.DTOS.TransactionDTO;
import com.albertocode.PaymentAPI.Repositories.TransactionRepository;
import com.albertocode.PaymentAPI.domain.Transaction.Transaction;
import com.albertocode.PaymentAPI.domain.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender,transaction.value());

//        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
//        if (!isAuthorized){
//            throw new Exception("Transaction not Authorized");
//        }


        Transaction newtransaction = new Transaction();
        newtransaction.setAmount(transaction.value());
        newtransaction.setSender(sender);
        newtransaction.setReceiver(receiver);
        newtransaction.setTimestamp(LocalDateTime.now());






        this.repository.save(newtransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender,"Transaction Completed Successfully");
        this.notificationService.sendNotification(receiver,"Transaction Received Successfully");


        return newtransaction;

    }

    public String authorizeTransaction(User sender, BigDecimal value) throws Exception {
//       ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize" , Map.class);
//
//       if (authorizationResponse.getStatusCode() == HttpStatus.OK ){
//           String message = (String) authorizationResponse.getBody() .get("data");
//           return "success".equalsIgnoreCase(message);
//       }else return false;
        throw new Exception("Transaction not Authorized");
    }
}
