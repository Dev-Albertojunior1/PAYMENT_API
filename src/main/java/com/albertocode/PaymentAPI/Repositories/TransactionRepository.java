package com.albertocode.PaymentAPI.Repositories;

import com.albertocode.PaymentAPI.domain.Transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}