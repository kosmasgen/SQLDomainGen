package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data transfer object for UserContactinfo.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserContactinfoDto {

    @NotNull
    private UUID id;

    private Integer chamberId;

    @NotNull
    private UUID companyId;

    @NotNull
    @Size(max = 255)
    private String username;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String email;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String phone;

    @Size(max = 255)
    private String mobile;

    @Size(max = 255)
    private String contactUrl;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

}
