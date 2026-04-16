package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data transfer object for Languages.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguagesDto {

    private UUID id;

    @NotNull
    private Integer chamberId;

    @NotNull
    @Size(max = 3)
    private String cd;

    @NotNull
    @Size(max = 50)
    private String descr;

    private Integer chamberLanguageId;

}
