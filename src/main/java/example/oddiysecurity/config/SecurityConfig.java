package example.oddiysecurity.config;

import example.oddiysecurity.enums.Role;
import example.oddiysecurity.service.SecurityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static example.oddiysecurity.enums.Permission.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SecurityUserService service;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->
                        (request
                                .requestMatchers("/api/v1/admin").hasRole("ADMIN")
                                .requestMatchers("/api/v1/home").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/api/v1/auth/sign").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/product").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/product")).permitAll()//hasAnyRole("ADMIN","USER")
                                .requestMatchers(HttpMethod.GET,"/api/v1/product/{id}").hasAnyRole("ADMIN","USER")
                                .requestMatchers(HttpMethod.PUT,"api/v1/product").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"api/v1/product").hasRole("ADMIN")
                                .anyRequest().authenticated()
                ).formLogin(s->{
                    s.loginPage("api/v1/auth/sign")
                        .successForwardUrl("api/v1/home")
                        .failureForwardUrl("api/v1/auth");
                });
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
