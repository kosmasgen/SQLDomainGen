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
 * Data transfer object for CompanyProfile.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyProfileDto {

    @NotNull
    private UUID id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String addressCity;

    @Size(max = 255)
    private String addressLatitude;

    @Size(max = 255)
    private String addressLongitude;

    @Size(max = 255)
    private String addressRegion;

    @Size(max = 255)
    private String addressStreet;

    @Size(max = 255)
    private String addressStreetNumber;

    @Size(max = 255)
    private String addressZipCode;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactEmail;

    @Size(max = 255)
    private String contactMobile;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhone1;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhone2;

    @Size(max = 255)
    @Pattern(regexp = ".*")
    private String contactPhone3;

    @Size(max = 255)
    private String contactUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    private UUID businessLocationId;

    @NotNull
    private UUID companyId;

    private Boolean recDeleted;

    private Boolean showBusinessGuide;

}
