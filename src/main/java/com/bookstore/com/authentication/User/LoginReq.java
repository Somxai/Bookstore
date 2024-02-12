package com.bookstore.com.authentication.User;

import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;

@Data
@Cacheable
public class LoginReq implements Serializable {

    private String email;

    private String password;


}
