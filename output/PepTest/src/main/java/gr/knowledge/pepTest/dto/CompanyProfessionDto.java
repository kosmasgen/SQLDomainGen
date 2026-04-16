package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.dto.ProfessionKindDto;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import java.math.BigInteger;

/**
 * Data transfer object for CompanyProfession.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyProfessionDto {

    private UUID id;

    @NotNull
    private Integer chamberId;

    private Integer chamberCompanyProfessionId;

    @NotNull
    private CompanyDto company;

    @NotNull
    private ProfessionDto profession;

    private ProfessionKindDto professionKind;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private Boolean recdeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toDate;

    private CompanyProfileDto profile;

    private BigInteger gemiId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gemiDateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gemiLastUpdated;

}
