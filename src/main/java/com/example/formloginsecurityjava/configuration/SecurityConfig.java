/**
 * ===============================================================
 * File name : SecurityConfig.java
 * Created by injeahwang on 2021-09-29
 * ===============================================================
 */
package com.example.formloginsecurityjava.configuration;

import com.example.formloginsecurityjava.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }

    /* rolehieracy ????????? ?????? SecurityExpressionHandler */
    public SecurityExpressionHandler expressionHandler() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        return handler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .mvcMatchers("/", "/signup").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN") // ????????? ????????? ????????? role??? ??????
                .mvcMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()                  //??????????????? ????????? ????????? ?????? ??????
                .expressionHandler(expressionHandler());

        http.formLogin()
                .loginPage("/login")
                .permitAll()        //?????? ???????????? ??????????????? permitAll ????????? ??????.
        ;

        /**lesson 33. BasicAuthenticationFilter*/
        http.httpBasic(); //????????? BASE64??? ?????????/?????????

        /** lesson 29 */
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.exceptionHandling()
                /** Access-denined ????????? ?????? ??? ???????????? ????????? ?????? ?????? ???????????? accessDeniedHandler??? ???????????????
                 *  ????????? ???????????? ?????? ?????? ????????????.
                 * */
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    System.out.println(principal.getUsername() + " is denied to access" + request.getRequestURI());
                    response.sendRedirect("/access-denied");    //??????????????? access-denied ???????????? ????????????.
                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService);
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

}
