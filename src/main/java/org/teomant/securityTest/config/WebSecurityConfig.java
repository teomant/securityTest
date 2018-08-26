package org.teomant.securityTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    DataSource dataSource;

    //BAP
    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    //оба
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    //логин/пароль
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    //Авторизация через страницу логина и перемещение по страницам
//        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
//                .and()
//                .authorizeRequests().antMatchers("/").authenticated()
//                .and()
//                .authorizeRequests().antMatchers("/login**").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/registration").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/user/**").hasAuthority("ROLE_USER")
//                .and()
//                .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/login").permitAll()
//                .and()
//                .rememberMe().rememberMeParameter("remember-me")
//                .tokenRepository(persistentTokenRepository()).tokenValiditySeconds(38400)
//                .and()
//                .csrf().disable();

        //Авторизация с помощью AuthenticationEntryPoint, наследника BasicAuthenticationEntryPoint
        http.csrf().disable();

        http.authorizeRequests().anyRequest().authenticated();

        http.httpBasic().authenticationEntryPoint(authEntryPoint);
    }

    //для страницы логина/пароля
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
//        repo.setDataSource(dataSource);
//        return repo;
//    }


}