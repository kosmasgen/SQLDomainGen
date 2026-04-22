package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data transfer object for CompanyFile.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyFileDto {

    private UUID id;

    private Integer chamberId;

    @NotNull
    @Size(max = 100)
    private String fileName;

    @NotNull
    private Integer fileSize;

    @NotNull
    @Size(max = 2000)
    private String blobUri;

    @NotNull
    private Integer orderSeq;

    private Boolean recdeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private CompanyDto company;

    private LanguagesDto language;

    private Boolean isLogo;

    private Boolean isBackground;

    private CompanyProfileDto companyProfile;

    @NotNull
    private Boolean isEmbedded;

}
