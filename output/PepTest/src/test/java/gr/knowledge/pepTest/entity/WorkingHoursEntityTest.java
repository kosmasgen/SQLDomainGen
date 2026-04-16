package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalTime;

class WorkingHoursEntityTest {

    /**
     * Tests the WorkingHours no-args constructor.
     */
    @Test
    void testWorkingHoursNoArgsConstructor() {
        WorkingHours entity = new WorkingHours();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the WorkingHours all-args constructor.
     */
    @Test
    void testWorkingHoursAllArgsConstructor() {
        Long id = 1L;
        Company company = new Company();
        String dayOfWeek = "test-value";
        LocalTime openingTime = LocalTime.of(10, 0, 0);
        LocalTime closingTime = LocalTime.of(10, 0, 0);
        Boolean isClosed = Boolean.TRUE;
        CompanyProfile profile = new CompanyProfile();

        WorkingHours entity = new WorkingHours(id, company, dayOfWeek, openingTime, closingTime, isClosed, profile);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getDayOfWeek()).isEqualTo(dayOfWeek);
        assertThat(entity.getOpeningTime()).isEqualTo(openingTime);
        assertThat(entity.getClosingTime()).isEqualTo(closingTime);
        assertThat(entity.getIsClosed()).isEqualTo(isClosed);
        assertThat(entity.getProfile()).isEqualTo(profile);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testWorkingHoursSettersAndGetters() {
        WorkingHours entity = new WorkingHours();

        Long id = 1L;
        Company company = new Company();
        String dayOfWeek = "test-value";
        LocalTime openingTime = LocalTime.of(10, 0, 0);
        LocalTime closingTime = LocalTime.of(10, 0, 0);
        Boolean isClosed = Boolean.TRUE;
        CompanyProfile profile = new CompanyProfile();

        entity.setId(id);
        entity.setCompany(company);
        entity.setDayOfWeek(dayOfWeek);
        entity.setOpeningTime(openingTime);
        entity.setClosingTime(closingTime);
        entity.setIsClosed(isClosed);
        entity.setProfile(profile);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getDayOfWeek()).isEqualTo(dayOfWeek);
        assertThat(entity.getOpeningTime()).isEqualTo(openingTime);
        assertThat(entity.getClosingTime()).isEqualTo(closingTime);
        assertThat(entity.getIsClosed()).isEqualTo(isClosed);
        assertThat(entity.getProfile()).isEqualTo(profile);
    }

}
