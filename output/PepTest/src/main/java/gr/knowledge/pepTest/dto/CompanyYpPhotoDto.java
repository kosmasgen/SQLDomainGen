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
 * Data transfer object for CompanyYpPhoto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyYpPhotoDto {

    private UUID id;

    @NotNull
    private Integer chamberId;

    @NotNull
    private CompanyDto company;

    @NotNull
    @Size(max = 100)
    private String fileName;

    @NotNull
    @Size(max = 100)
    private String mimeType;

    @NotNull
    private Integer fileSize;

    private String title;

    @NotNull
    private Integer orderSeq;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private Boolean recdeleted;

    @NotNull
    @Size(max = 2000)
    private String blobUri;

}
