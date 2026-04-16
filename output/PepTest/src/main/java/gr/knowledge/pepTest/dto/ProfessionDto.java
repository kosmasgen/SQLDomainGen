package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigInteger;
import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;

/**
 * Data transfer object for Profession.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessionDto {

    private UUID id;

    @NotNull
    private Integer chamberId;

    private Integer chamberProfessionId;

    private ProfessionDto parentProfession;

    @NotNull
    private ProfessionSystemDto professionSystem;

    @NotNull
    @Size(max = 255)
    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private Boolean recdeleted;

    private BigInteger proteasId;

    private ProfessionFriendlyCategoryDto friendlyCat;

}
