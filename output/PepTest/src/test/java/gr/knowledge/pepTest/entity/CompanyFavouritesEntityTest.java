package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyFavouritesEntityTest {

    /**
     * Tests the CompanyFavourites no-args constructor.
     */
    @Test
    void testCompanyFavouritesNoArgsConstructor() {
        CompanyFavourites entity = new CompanyFavourites();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyFavourites all-args constructor.
     */
    @Test
    void testCompanyFavouritesAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Company company = new Company();
        Company favouriteCompany = new Company();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String notes = "test-value";
        CompanyProfile favouriteProfile = new CompanyProfile();

        CompanyFavourites entity = new CompanyFavourites(id, company, favouriteCompany, dateCreated, lastUpdated, notes, favouriteProfile);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getFavouriteCompany()).isEqualTo(favouriteCompany);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getNotes()).isEqualTo(notes);
        assertThat(entity.getFavouriteProfile()).isEqualTo(favouriteProfile);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyFavouritesSettersAndGetters() {
        CompanyFavourites entity = new CompanyFavourites();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Company company = new Company();
        Company favouriteCompany = new Company();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        String notes = "test-value";
        CompanyProfile favouriteProfile = new CompanyProfile();

        entity.setId(id);
        entity.setCompany(company);
        entity.setFavouriteCompany(favouriteCompany);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setNotes(notes);
        entity.setFavouriteProfile(favouriteProfile);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getFavouriteCompany()).isEqualTo(favouriteCompany);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getNotes()).isEqualTo(notes);
        assertThat(entity.getFavouriteProfile()).isEqualTo(favouriteProfile);
    }

}
