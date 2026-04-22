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
 * Data transfer object for CompanyProfile.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyProfileDto {

    private UUID id;

    @NotNull
    @Size(max = 1000)
    private String name;

    @Size(max = 50)
    private String addressCity;

    @Size(max = 255)
    private String addressLatitude;

    @Size(max = 255)
    private String addressLongitude;

    @Size(max = 50)
    private String addressRegion;

    @Size(max = 255)
    private String addressStreet;

    @Size(max = 255)
    private String addressStreetNumber;

    @Size(max = 12)
    private String addressZipCode;

    @Size(max = 100)
    private String contactEmail;

    @Size(max = 50)
    private String contactMobile;

    @Size(max = 20)
    private String contactPhone1;

    @Size(max = 20)
    private String contactPhone2;

    @Size(max = 20)
    private String contactPhone3;

    @Size(max = 100)
    private String contactUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    private BusinessLocationDto businessLocation;

    @NotNull
    private CompanyDto company;

    private Boolean recdeleted;

    private Boolean showBusinessGuide;

}
