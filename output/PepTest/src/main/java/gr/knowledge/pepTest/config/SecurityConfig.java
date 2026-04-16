package gr.knowledge.pepTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration.
 * <p>
 * TODO: Replace default configuration with proper authentication (JWT/OAuth2)
 * and restrict access rules before production use.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures HTTP security.
     * @param http HTTP security configuration
     * @return configured SecurityFilterChain
     * @throws Exception if security configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().permitAll() // TODO restrict endpoints (authenticated/roles)
                )
                .httpBasic(Customizer.withDefaults()); // TODO replace with JWT or OAuth2

        return http.build();
    }
}
