/**
 * ===============================================================
 * File name : Account.java
 * Created by injeahwang on 2021-03-19
 * ===============================================================
 */
package com.example.formloginsecurityjava.account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Account {

    @Id @GeneratedValue
    private Integer id;

    @Column(unique=true)
    private String username;

    private String password;

    private String role;

    /** lesson 6 */
    public void encodePassword(){
        /* spring security에서는 원하는 패스워드 인코딩 타입이 따로 있다 */
        this.setPassword("{noop}"+this.getPassword());
    }

    /** lesson-7 : passwordEncoder*/
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }
}
