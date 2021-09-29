/**
 * ===============================================================
 * File name : AccountRepository.java
 * Created by injeahwang on 2021-03-19
 * ===============================================================
 */
package com.example.formloginsecurityjava.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
