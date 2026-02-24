package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Data transfer object for DataStaging.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataStagingDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 255)
    private String legacyTableName;

    @NotNull
    @Size(max = 255)
    private String legacyRecordId;

    @NotNull
    @Size(max = 255)
    private String rawData;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime legacyUpdatedAt;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pulledAt;

    @NotNull
    @Size(max = 255)
    private String status;

}
