package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import gr.knowledge.pepTest.dto.CompanyDto;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import gr.knowledge.pepTest.dto.CompanyProfileDto;

/**
 * Data transfer object for CompanyFavourites.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyFavouritesDto {

    private UUID id;

    @NotNull
    private CompanyDto company;

    @NotNull
    private CompanyDto favouriteCompany;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    private String notes;

    private CompanyProfileDto favouriteProfile;

}
