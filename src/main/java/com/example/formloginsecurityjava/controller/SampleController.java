/**
 * ===============================================================
 * File name : SampleController.java
 * Created by injeahwang on 2021-03-19
 * ===============================================================
 */
package com.example.formloginsecurityjava.controller;

import com.example.formloginsecurityjava.account.Account;
import com.example.formloginsecurityjava.account.AccountRepository;
import com.example.formloginsecurityjava.common.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class SampleController {


    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    /** lesson 45
     *  account를 바로 받을 수 있도록 한다.
     * @AuthenticationPrincipal에 붙는 옵션이 너무 길어 별도의 커스텀한 어노테이션을 정의할 수도 있다.
     * */
    public String index(Model model, @CurrentUser Account account){
        if(account == null){
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello "+account.getUsername());
        }
        return "index";
    }

    @GetMapping("/user")
    public String info(Model model){
        model.addAttribute("message", "user");
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        model.addAttribute("message", "Hello admin, "+principal.getName());
        return "admin";
    }

}
