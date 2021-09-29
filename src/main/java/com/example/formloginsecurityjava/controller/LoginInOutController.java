/**
 * ===============================================================
 * File name : LoginInOutController.java
 * Created by injeahwang on 2021-05-18
 * ===============================================================
 */
package com.example.formloginsecurityjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginInOutController {

    /** lesson32. get요청을 처리하는 컨트롤러 설정 필요 */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }


}
