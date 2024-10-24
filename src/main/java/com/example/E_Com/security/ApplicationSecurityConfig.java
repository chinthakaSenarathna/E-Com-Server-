package com.example.E_com.security;

import com.example.E_com.jwt.JwtConfig;
import com.example.E_com.jwt.JwtTokenVerifire;
import com.example.E_com.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.example.E_com.service.impl.ApplicationUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true ) // we can enable method level security... -> @PreAuthorize()
public class ApplicationSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserServiceImpl applicationUserService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public ApplicationSecurityConfig(
            PasswordEncoder passwordEncoder,
            ApplicationUserServiceImpl applicationUserService,
            SecretKey secretKey,
            JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedHeaders(List.of(
                "Authorization", "Cache-Control", "Content-Type"
        ));

        // only can access, "www.abc.com"
//        corsConfiguration.setAllowedOrigins(List.of("http://www.abc.com"));
        corsConfiguration.setAllowedOrigins(List.of("*"));

        corsConfiguration.setAllowedMethods(List.of(
                "POST", "GET", "PUT", "PATCH", "DELETE", "OPTIONS"
        ));

        corsConfiguration.setAllowCredentials(false);

        // can be accessed by the browser from the server's response.
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        // what is the class for User verify, Token verify, Token create
        http
                // Cross Site Request Forgecy -> disable
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> corsConfiguration))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Username & Password is correct(Authenticate), then try to Authorize(Create token & Exposed)
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(http), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifire(jwtConfig, secretKey), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                // without the token can access...
                                "/api/v1/user/register/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated());

        return http.build();

    }

    // use to get the database user...
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        // ApplicationUserServiceImpl, done login by using this class
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity){
        return httpSecurity.getSharedObject(AuthenticationManager.class);
    }

    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    }
}
