package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyFavouritesDtoTest {

    /**
     * Tests the CompanyFavouritesDto no-args constructor
     */
    @Test
    void testCompanyFavouritesDtoNoArgsConstructor() {
        CompanyFavouritesDto dto = new CompanyFavouritesDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getFavouriteCompany()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getNotes()).isNull();
        assertThat(dto.getFavouriteProfile()).isNull();
    }

    /**
     * Tests the CompanyFavouritesDto all-args constructor
     */
    @Test
    void testCompanyFavouritesDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyDto company = new CompanyDto();
        CompanyDto favouriteCompany = new CompanyDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String notes = "test-value";
        CompanyProfileDto favouriteProfile = new CompanyProfileDto();

        CompanyFavouritesDto dto = new CompanyFavouritesDto(id, company, favouriteCompany, dateCreated, lastUpdated, notes, favouriteProfile);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getFavouriteCompany()).isEqualTo(favouriteCompany);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getNotes()).isEqualTo(notes);
        assertThat(dto.getFavouriteProfile()).isEqualTo(favouriteProfile);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyFavouritesDtoSettersAndGetters() {
        CompanyFavouritesDto dto = new CompanyFavouritesDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyDto company = new CompanyDto();
        CompanyDto favouriteCompany = new CompanyDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        String notes = "test-value";
        CompanyProfileDto favouriteProfile = new CompanyProfileDto();

        dto.setId(id);
        dto.setCompany(company);
        dto.setFavouriteCompany(favouriteCompany);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setNotes(notes);
        dto.setFavouriteProfile(favouriteProfile);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getFavouriteCompany()).isEqualTo(favouriteCompany);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getNotes()).isEqualTo(notes);
        assertThat(dto.getFavouriteProfile()).isEqualTo(favouriteProfile);
    }

}
