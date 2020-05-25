package sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password, true from user where username=?")
                .authoritiesByUsernameQuery("select username, role from user where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/administratorHome/**").hasRole("Administrator")
                .antMatchers("/doctorHome/**").hasRole("Doctor")
                .antMatchers("/secretaryHome/**").hasRole("Secretary")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(getAuthenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure (WebSecurity webSecurity) throws Exception{
        webSecurity
                .ignoring()
                .antMatchers("/resources/**");
    }

    private AuthetificationSuccessHandler getAuthenticationSuccessHandler(){
        return new AuthetificationSuccessHandler();
    }


}
