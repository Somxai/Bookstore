package com.bookstore.com.authentication.User;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;

@Data
@Builder
@Cacheable
public class LoginResponse implements Serializable {
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private String token;

}
