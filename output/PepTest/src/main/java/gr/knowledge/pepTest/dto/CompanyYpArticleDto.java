package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import gr.knowledge.pepTest.dto.CompanyDto;
import jakarta.validation.constraints.NotNull;
import gr.knowledge.pepTest.dto.LanguagesDto;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import gr.knowledge.pepTest.dto.CompanyProfileDto;

/**
 * Data transfer object for CompanyYpArticle.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyYpArticleDto {

    private UUID id;

    private Integer chamberId;

    @NotNull
    private CompanyDto company;

    private String title;

    private String html;

    private LanguagesDto language;

    @NotNull
    private Integer orderSeq;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private Boolean recdeleted;

    @NotNull
    private Boolean isPublished;

    @NotNull
    private CompanyProfileDto companyProfile;

}
