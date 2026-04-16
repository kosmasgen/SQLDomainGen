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
     * Creates a {@link ModelMapper} instance configured for PATCH support.
     * <p>
     * Important: null values are skipped during mapping.
     *
     * @return a configured ModelMapper bean
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);

        return modelMapper;
    }
}
