package com.albertocode.PaymentAPI.Repositories;

import com.albertocode.PaymentAPI.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   Optional<User> findUserByDocument(String document);

   Optional<User> findUserById(Long id);
}
