/**
 * ===============================================================
 * File name : UserAccount.java
 * Created by injeahwang on 2021-05-19
 * ===============================================================
 */
package com.example.formloginsecurityjava.account;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/** lesson 45. */
public class UserAccount extends User {

    private Account account;

    public UserAccount(Account account) {
        super(account.getUsername(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_"+account.getRole())));
        this.account = account;
    }

    public Account getAccount(){
        return account;
    }
}
