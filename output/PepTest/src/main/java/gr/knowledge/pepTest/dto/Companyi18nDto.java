package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data transfer object for Companyi18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Companyi18nDto {

    @NotNull
    private UUID companyId;

    @NotNull
    private UUID languageId;

    @NotNull
    private Integer chamberI18nId;

    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String coName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @Size(max = 255)
    private String objective;

    @NotNull
    private Boolean recDeleted;

    @Size(max = 255)
    private String street;

    @Size(max = 255)
    private String responsibleName;

}
