package gr.knowledge.schoolmanagement.dto;

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
 * Data transfer object for School.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchoolDto {

    @NotNull(message = "{validation.school.id.notnull}")
    @Size(max = 255, message = "{validation.school.id.size}")
    private Long id;

    @NotNull(message = "{validation.school.name.notnull}")
    @Size(max = 255, message = "{validation.school.name.size}")
    private String name;

    @Size(max = 255, message = "{validation.school.address.size}")
    private String address;

    @Size(max = 100, message = "{validation.school.city.size}")
    private String city;

    @Size(max = 20, message = "{validation.school.zipcode.size}")
    private String zipcode;

    @NotNull(message = "{validation.school.createdAt.notnull}")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Size(max = 255, message = "{validation.school.createdAt.size}")
    private LocalDateTime createdAt;

}
