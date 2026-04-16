package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyProfileEntityTest {

    /**
     * Tests the CompanyProfile no-args constructor.
     */
    @Test
    void testCompanyProfileNoArgsConstructor() {
        CompanyProfile entity = new CompanyProfile();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyProfile all-args constructor.
     */
    @Test
    void testCompanyProfileAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String name = "test-value";
        String addressCity = "test-value";
        String addressLatitude = "test-value";
        String addressLongitude = "test-value";
        String addressRegion = "test-value";
        String addressStreet = "test-value";
        String addressStreetNumber = "test-value";
        String addressZipCode = "test-value";
        String contactEmail = "test-value";
        String contactMobile = "test-value";
        String contactPhone1 = "test-value";
        String contactPhone2 = "test-value";
        String contactPhone3 = "test-value";
        String contactUrl = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BusinessLocation businessLocation = new BusinessLocation();
        Company company = new Company();
        Boolean recdeleted = Boolean.TRUE;
        Boolean showBusinessGuide = Boolean.TRUE;

        CompanyProfile entity = new CompanyProfile(id, name, addressCity, addressLatitude, addressLongitude, addressRegion, addressStreet, addressStreetNumber, addressZipCode, contactEmail, contactMobile, contactPhone1, contactPhone2, contactPhone3, contactUrl, dateCreated, lastUpdated, businessLocation, company, recdeleted, showBusinessGuide);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getName()).isEqualTo(name);
        assertThat(entity.getAddressCity()).isEqualTo(addressCity);
        assertThat(entity.getAddressLatitude()).isEqualTo(addressLatitude);
        assertThat(entity.getAddressLongitude()).isEqualTo(addressLongitude);
        assertThat(entity.getAddressRegion()).isEqualTo(addressRegion);
        assertThat(entity.getAddressStreet()).isEqualTo(addressStreet);
        assertThat(entity.getAddressStreetNumber()).isEqualTo(addressStreetNumber);
        assertThat(entity.getAddressZipCode()).isEqualTo(addressZipCode);
        assertThat(entity.getContactEmail()).isEqualTo(contactEmail);
        assertThat(entity.getContactMobile()).isEqualTo(contactMobile);
        assertThat(entity.getContactPhone1()).isEqualTo(contactPhone1);
        assertThat(entity.getContactPhone2()).isEqualTo(contactPhone2);
        assertThat(entity.getContactPhone3()).isEqualTo(contactPhone3);
        assertThat(entity.getContactUrl()).isEqualTo(contactUrl);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getBusinessLocation()).isEqualTo(businessLocation);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getShowBusinessGuide()).isEqualTo(showBusinessGuide);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyProfileSettersAndGetters() {
        CompanyProfile entity = new CompanyProfile();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String name = "test-value";
        String addressCity = "test-value";
        String addressLatitude = "test-value";
        String addressLongitude = "test-value";
        String addressRegion = "test-value";
        String addressStreet = "test-value";
        String addressStreetNumber = "test-value";
        String addressZipCode = "test-value";
        String contactEmail = "test-value";
        String contactMobile = "test-value";
        String contactPhone1 = "test-value";
        String contactPhone2 = "test-value";
        String contactPhone3 = "test-value";
        String contactUrl = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BusinessLocation businessLocation = new BusinessLocation();
        Company company = new Company();
        Boolean recdeleted = Boolean.TRUE;
        Boolean showBusinessGuide = Boolean.TRUE;

        entity.setId(id);
        entity.setName(name);
        entity.setAddressCity(addressCity);
        entity.setAddressLatitude(addressLatitude);
        entity.setAddressLongitude(addressLongitude);
        entity.setAddressRegion(addressRegion);
        entity.setAddressStreet(addressStreet);
        entity.setAddressStreetNumber(addressStreetNumber);
        entity.setAddressZipCode(addressZipCode);
        entity.setContactEmail(contactEmail);
        entity.setContactMobile(contactMobile);
        entity.setContactPhone1(contactPhone1);
        entity.setContactPhone2(contactPhone2);
        entity.setContactPhone3(contactPhone3);
        entity.setContactUrl(contactUrl);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setBusinessLocation(businessLocation);
        entity.setCompany(company);
        entity.setRecdeleted(recdeleted);
        entity.setShowBusinessGuide(showBusinessGuide);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getName()).isEqualTo(name);
        assertThat(entity.getAddressCity()).isEqualTo(addressCity);
        assertThat(entity.getAddressLatitude()).isEqualTo(addressLatitude);
        assertThat(entity.getAddressLongitude()).isEqualTo(addressLongitude);
        assertThat(entity.getAddressRegion()).isEqualTo(addressRegion);
        assertThat(entity.getAddressStreet()).isEqualTo(addressStreet);
        assertThat(entity.getAddressStreetNumber()).isEqualTo(addressStreetNumber);
        assertThat(entity.getAddressZipCode()).isEqualTo(addressZipCode);
        assertThat(entity.getContactEmail()).isEqualTo(contactEmail);
        assertThat(entity.getContactMobile()).isEqualTo(contactMobile);
        assertThat(entity.getContactPhone1()).isEqualTo(contactPhone1);
        assertThat(entity.getContactPhone2()).isEqualTo(contactPhone2);
        assertThat(entity.getContactPhone3()).isEqualTo(contactPhone3);
        assertThat(entity.getContactUrl()).isEqualTo(contactUrl);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getBusinessLocation()).isEqualTo(businessLocation);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getShowBusinessGuide()).isEqualTo(showBusinessGuide);
    }

}
