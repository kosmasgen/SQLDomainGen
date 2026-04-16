package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalTime;

class WorkingHoursDtoTest {

    /**
     * Tests the WorkingHoursDto no-args constructor
     */
    @Test
    void testWorkingHoursDtoNoArgsConstructor() {
        WorkingHoursDto dto = new WorkingHoursDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getDayOfWeek()).isNull();
        assertThat(dto.getOpeningTime()).isNull();
        assertThat(dto.getClosingTime()).isNull();
        assertThat(dto.getIsClosed()).isNull();
        assertThat(dto.getProfile()).isNull();
    }

    /**
     * Tests the WorkingHoursDto all-args constructor
     */
    @Test
    void testWorkingHoursDtoAllArgsConstructor() {
        Long id = 1L;
        CompanyDto company = new CompanyDto();
        String dayOfWeek = "test-value";
        LocalTime openingTime = LocalTime.of(10, 0);
        LocalTime closingTime = LocalTime.of(10, 0);
        Boolean isClosed = Boolean.TRUE;
        CompanyProfileDto profile = new CompanyProfileDto();

        WorkingHoursDto dto = new WorkingHoursDto(id, company, dayOfWeek, openingTime, closingTime, isClosed, profile);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getDayOfWeek()).isEqualTo(dayOfWeek);
        assertThat(dto.getOpeningTime()).isEqualTo(openingTime);
        assertThat(dto.getClosingTime()).isEqualTo(closingTime);
        assertThat(dto.getIsClosed()).isEqualTo(isClosed);
        assertThat(dto.getProfile()).isEqualTo(profile);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testWorkingHoursDtoSettersAndGetters() {
        WorkingHoursDto dto = new WorkingHoursDto();

        Long id = 1L;
        CompanyDto company = new CompanyDto();
        String dayOfWeek = "test-value";
        LocalTime openingTime = LocalTime.of(10, 0);
        LocalTime closingTime = LocalTime.of(10, 0);
        Boolean isClosed = Boolean.TRUE;
        CompanyProfileDto profile = new CompanyProfileDto();

        dto.setId(id);
        dto.setCompany(company);
        dto.setDayOfWeek(dayOfWeek);
        dto.setOpeningTime(openingTime);
        dto.setClosingTime(closingTime);
        dto.setIsClosed(isClosed);
        dto.setProfile(profile);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getDayOfWeek()).isEqualTo(dayOfWeek);
        assertThat(dto.getOpeningTime()).isEqualTo(openingTime);
        assertThat(dto.getClosingTime()).isEqualTo(closingTime);
        assertThat(dto.getIsClosed()).isEqualTo(isClosed);
        assertThat(dto.getProfile()).isEqualTo(profile);
    }

}
