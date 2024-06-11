package com.albertocode.PaymentAPI.DTOS;

import com.albertocode.PaymentAPI.domain.User.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {


}
