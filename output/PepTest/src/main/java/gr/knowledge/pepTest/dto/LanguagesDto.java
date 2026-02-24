package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

/**
 * Data transfer object for Languages.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguagesDto {

    @NotNull
    private UUID id;

    @NotNull
    private Integer chamberId;

    @NotNull
    @Size(max = 255)
    private String cd;

    @NotNull
    @Size(max = 255)
    private String descr;

    private Integer chamberLanguageId;

}
