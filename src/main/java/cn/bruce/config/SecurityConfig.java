package cn.bruce.config;

import cn.bruce.security.browser.MyPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public void configureClobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
        auth
                .inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("admin").password("123456").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form") // 设置登录请求提交的数据与 UsernamePasswordAuthenticationFilter 过滤器的映射
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require").permitAll()
                .antMatchers("/publicResources/", "/signup", "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .httpBasic();
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                //.logoutSuccessHandler(logoutSu)
                .invalidateHttpSession(true)
                // .addLogoutHandler(LogoutHandler)
                .and();
    }
}
