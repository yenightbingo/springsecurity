package com.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class MySecurity extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        //定制请求授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
    http.formLogin().usernameParameter("username").passwordParameter("pwd").loginPage("/userlogin");

    http.logout().logoutSuccessUrl("/");

    http.rememberMe().rememberMeParameter("rememberme");

    }


   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        auth.inMemoryAuthentication().withUser("zhangsan").password("123456").roles("vip1","vip2")
                .and()
                .withUser("lisi").password("123456").roles("vip2","vip3")
                .and()
                .withUser("wangwu").password("123456").roles("vip1","vip3");


    }*/

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("zhangsan")
                        .password("123456")
                        .roles("vip1","vip2")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }


}
