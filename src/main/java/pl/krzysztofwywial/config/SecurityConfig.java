package pl.krzysztofwywial.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .antMatchers("/").permitAll()
                .antMatchers("/h2").permitAll()
                .antMatchers("/images/**").permitAll()
                .anyRequest().authenticated());
        http.formLogin(form -> form
                .loginPage("/logowanie")
                .loginProcessingUrl("/login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .permitAll());
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/farewell").permitAll());
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}