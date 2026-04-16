package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import gr.knowledge.pepTest.entity.MunicipalityI18nKey;
import gr.knowledge.pepTest.dto.MunicipalityDto;
import jakarta.validation.constraints.NotNull;
import gr.knowledge.pepTest.dto.LanguagesDto;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for MunicipalityI18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MunicipalityI18nDto {

    private MunicipalityI18nKey id;

    @NotNull
    private MunicipalityDto municipality;

    @NotNull
    private LanguagesDto language;

    @NotNull
    @Size(max = 255)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private Boolean recdeleted;

    private Integer chamberI18nId;

}
