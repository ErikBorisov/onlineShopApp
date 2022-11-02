package com.example.onlineapp.configuration;

import com.example.onlineapp.handler.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("currentUserDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Value("${spring.social.facebook.appSecret}")
//    String appSecret;
//
//    @Value("${spring.social.facebook.appId}")
//    String appId;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/activate").permitAll()
                .antMatchers("/user/image").permitAll()
                .antMatchers("/images/**").permitAll()
//                .antMatchers("/seeMessages").hasAnyAuthority("user") //example
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
//                .antMatchers("/admin/**").hasAuthority("ADMIN")

                .failureUrl("/login?error=Bad credentials")
                .failureHandler(new CustomAuthenticationFailureHandler())
                .permitAll()
                .and()
//                .oauth2Login()
//                .loginPage("/login")
//                .userInfoEndpoint()
//                .userService(oauth2UserService)
//                .and()
//                .successHandler(oauth2LoginSuccessHandler)
//                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe()
                .tokenValiditySeconds(2 * 604800) // 1 week
                .and();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//@Configuration
//@EnableWebMvc
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    @Qualifier("currentUserDetailServiceImpl")
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/user/home")
//                .failureUrl("/login?error=Bad credentials")
//                .failureHandler(new CustomAuthenticationFailureHandler())
//                .permitAll()
//                .and();
////                .logout()
////                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))
////                .invalidateHttpSession(true)
////                .clearAuthentication(true)
////                .deleteCookies("JSESSIONID")
////                .and()
////                .rememberMe()
////                .tokenValiditySeconds(2*604800)
////                .and();
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder);
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
