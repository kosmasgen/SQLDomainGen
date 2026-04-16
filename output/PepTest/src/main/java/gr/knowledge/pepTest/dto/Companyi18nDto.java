package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import gr.knowledge.pepTest.entity.Companyi18nKey;
import gr.knowledge.pepTest.dto.CompanyDto;
import jakarta.validation.constraints.NotNull;
import gr.knowledge.pepTest.dto.LanguagesDto;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for Companyi18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Companyi18nDto {

    private Companyi18nKey id;

    @NotNull
    private CompanyDto company;

    @NotNull
    private LanguagesDto language;

    @Size(max = 50)
    private String city;

    @Size(max = 1000)
    private String coName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    private String objective;

    @NotNull
    private Boolean recdeleted;

    @Size(max = 60)
    private String street;

    @Size(max = 255)
    private String responsibleName;

}
