/**
 * ===============================================================
 * File name : SignUpController.java
 * Created by injeahwang on 2021-05-10
 * ===============================================================
 */
package com.example.formloginsecurityjava.controller;

import com.example.formloginsecurityjava.account.Account;
import com.example.formloginsecurityjava.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @Autowired
    AccountService accountService;

    /** 회원 가입용 페이지 컨트롤러 */
    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("account", new Account());
        return "signup";
    }

    /** signup을 처리하는 API */
    @PostMapping("/signup")
    public String processSignUp(@ModelAttribute Account account) {
        account.setRole("USER");
        accountService.createNew(account);
        return "redirect:/";
    }

}