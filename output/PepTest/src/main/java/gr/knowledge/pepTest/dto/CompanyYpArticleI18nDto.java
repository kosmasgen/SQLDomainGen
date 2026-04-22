package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18nKey;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for CompanyYpArticleI18n.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyYpArticleI18nDto {

    private CompanyYpArticleI18nKey id;

    @NotNull
    private CompanyYpArticleDto companyArticle;

    private String title;

    private String html;

    @NotNull
    private LanguagesDto language;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private Boolean recdeleted;

}
