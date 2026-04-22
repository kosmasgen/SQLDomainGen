package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import gr.knowledge.pepTest.entity.ChAppUserContactI18nKey;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;

/**
 * Data transfer object for ChAppUserContactI18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChAppUserContactI18nDto {

    private ChAppUserContactI18nKey id;

    @NotNull
    private ChAppUserContactDto chAppUserContact;

    @NotNull
    private LanguagesDto language;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @Size(max = 50)
    private String city;

    @Size(max = 255)
    private String street;

    @NotNull
    private Boolean recdeleted;

}
