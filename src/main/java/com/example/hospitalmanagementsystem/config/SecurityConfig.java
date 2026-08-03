package com.example.hospitalmanagementsystem.config;
import com.example.hospitalmanagementsystem.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig
{

   @Autowired
   private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception
    {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()

                        // ADMIN only
                        .requestMatchers("/doctors/**").hasRole("ADMIN")

                        // ADMIN or RECEPTIONIST
                        .requestMatchers("/patients/**")
                        .hasAnyRole("ADMIN", "RECEPTIONIST")

                        // ADMIN, DOCTOR, RECEPTIONIST
                        .requestMatchers("/appointments/**")
                        .hasAnyRole("ADMIN", "DOCTOR", "RECEPTIONIST")
                        .anyRequest().authenticated()   )

                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class  );
              //  .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
