package gr.knowledge.pepTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS configuration.
 */
@Configuration
public class CorsConfig {

    /**
     * Configures CORS mappings.
     * @return configured WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@org.springframework.lang.NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*") // TODO set specific origins when allowCredentials(true)
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .exposedHeaders("X-Total-Count")
                        .allowCredentials(true);
            }
        };
    }
}
