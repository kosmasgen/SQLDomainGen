package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import gr.knowledge.pepTest.entity.ProfessionKindi18nKey;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for ProfessionKindi18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessionKindi18nDto {

    private ProfessionKindi18nKey id;

    @NotNull
    private ProfessionKindDto professionKind;

    @NotNull
    private LanguagesDto language;

    @NotNull
    private Boolean recdeleted;

    @NotNull
    @Size(max = 255)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    private Integer chamberI18nId;

}
