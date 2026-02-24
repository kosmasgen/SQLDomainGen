package gr.knowledge.pepTest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides the {@link ModelMapper} bean used by generated mappers.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates a {@link ModelMapper} instance for the application context.
     *
     * @return a ModelMapper bean
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
