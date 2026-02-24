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
 * Data transfer object for ChAppUserContact.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChAppUserContactDto {

    @NotNull
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private UUID chamberAppUserId;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String phone1;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String phone2;

    @Size(max = 255)
    private String mobile1;

    @Size(max = 255)
    private String mobile2;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String email1;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String email2;

    @Size(max = 255)
    private String url;

    @Size(max = 255)
    private String zipCode;

    @Size(max = 255)
    private String latitude;

    @Size(max = 255)
    private String longitude;

    @Size(max = 255)
    private String streetNumber;

    @NotNull
    private Boolean recDeleted;

    @Size(max = 255)
    private String listingUrl;

}
