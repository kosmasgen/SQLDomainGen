package gr.knowledge.pepTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data transfer object for ChAppUserContact.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChAppUserContactDto {

    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    @NotNull
    private ChamberAppUserDto chamberAppUser;

    @Size(max = 20)
    private String phone1;

    @Size(max = 20)
    private String phone2;

    @Size(max = 20)
    private String mobile1;

    @Size(max = 20)
    private String mobile2;

    @Size(max = 100)
    private String email1;

    @Size(max = 100)
    private String email2;

    @Size(max = 255)
    private String url;

    @Size(max = 12)
    private String zipCode;

    @Size(max = 255)
    private String latitude;

    @Size(max = 255)
    private String longitude;

    @Size(max = 255)
    private String streetNumber;

    @NotNull
    private Boolean recdeleted;

    @Size(max = 255)
    private String listingUrl;

    @Size(max = 255)
    private String email;

    @Size(max = 50)
    private String mobile;

}
