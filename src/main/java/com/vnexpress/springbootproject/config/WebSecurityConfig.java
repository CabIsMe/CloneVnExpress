//package com.vnexpress.springbootproject.config;
//
////import com.vnexpress.springbootproject.service.UserDetailsServiceImpl;
//import com.vnexpress.springbootproject.repository.user.UserRepository;
//import com.vnexpress.springbootproject.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.List;
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    public  BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public UserDetailsService userDetailService(){
//        return new UserDetailsServiceImpl();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/register.html","/vnexpress/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/content/login.html").loginProcessingUrl("/content/home")
//                .defaultSuccessUrl("/content/**", true)
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//
//
//        ;
//    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder());
//        provider.setUserDetailsService(userDetailService());
//
//        return provider;
//    }
//
//
////    @Bean
////    @Override
////    public UserDetailsService userDetailsService() {
////
////        UserDetails user=
////                User.withDefaultPasswordEncoder()
////                        .username("sa")
////                        .password("123")
////                        .roles("ADMIN")
////                        .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
//
//
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/home").hasAnyAuthority("USER")
////                .antMatchers("/content/**").hasAuthority("ADMIN")
////                .anyRequest().authenticated()
////                .and()
////                .formLogin().loginPage("/login.html").loginProcessingUrl("/login")
////                .defaultSuccessUrl("/home", true)
////                .permitAll()
////                .and()
////                .logout().permitAll()
////                .and()
////                .exceptionHandling().accessDeniedPage("/403")
////        ;
////    }
//}