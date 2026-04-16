package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyProfileDtoTest {

    /**
     * Tests the CompanyProfileDto no-args constructor
     */
    @Test
    void testCompanyProfileDtoNoArgsConstructor() {
        CompanyProfileDto dto = new CompanyProfileDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getName()).isNull();
        assertThat(dto.getAddressCity()).isNull();
        assertThat(dto.getAddressLatitude()).isNull();
        assertThat(dto.getAddressLongitude()).isNull();
        assertThat(dto.getAddressRegion()).isNull();
        assertThat(dto.getAddressStreet()).isNull();
        assertThat(dto.getAddressStreetNumber()).isNull();
        assertThat(dto.getAddressZipCode()).isNull();
        assertThat(dto.getContactEmail()).isNull();
        assertThat(dto.getContactMobile()).isNull();
        assertThat(dto.getContactPhone1()).isNull();
        assertThat(dto.getContactPhone2()).isNull();
        assertThat(dto.getContactPhone3()).isNull();
        assertThat(dto.getContactUrl()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getBusinessLocation()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getShowBusinessGuide()).isNull();
    }

    /**
     * Tests the CompanyProfileDto all-args constructor
     */
    @Test
    void testCompanyProfileDtoAllArgsConstructor() {
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
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BusinessLocationDto businessLocation = new BusinessLocationDto();
        CompanyDto company = new CompanyDto();
        Boolean recdeleted = Boolean.TRUE;
        Boolean showBusinessGuide = Boolean.TRUE;

        CompanyProfileDto dto = new CompanyProfileDto(id, name, addressCity, addressLatitude, addressLongitude, addressRegion, addressStreet, addressStreetNumber, addressZipCode, contactEmail, contactMobile, contactPhone1, contactPhone2, contactPhone3, contactUrl, dateCreated, lastUpdated, businessLocation, company, recdeleted, showBusinessGuide);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAddressCity()).isEqualTo(addressCity);
        assertThat(dto.getAddressLatitude()).isEqualTo(addressLatitude);
        assertThat(dto.getAddressLongitude()).isEqualTo(addressLongitude);
        assertThat(dto.getAddressRegion()).isEqualTo(addressRegion);
        assertThat(dto.getAddressStreet()).isEqualTo(addressStreet);
        assertThat(dto.getAddressStreetNumber()).isEqualTo(addressStreetNumber);
        assertThat(dto.getAddressZipCode()).isEqualTo(addressZipCode);
        assertThat(dto.getContactEmail()).isEqualTo(contactEmail);
        assertThat(dto.getContactMobile()).isEqualTo(contactMobile);
        assertThat(dto.getContactPhone1()).isEqualTo(contactPhone1);
        assertThat(dto.getContactPhone2()).isEqualTo(contactPhone2);
        assertThat(dto.getContactPhone3()).isEqualTo(contactPhone3);
        assertThat(dto.getContactUrl()).isEqualTo(contactUrl);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getBusinessLocation()).isEqualTo(businessLocation);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getShowBusinessGuide()).isEqualTo(showBusinessGuide);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyProfileDtoSettersAndGetters() {
        CompanyProfileDto dto = new CompanyProfileDto();

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
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BusinessLocationDto businessLocation = new BusinessLocationDto();
        CompanyDto company = new CompanyDto();
        Boolean recdeleted = Boolean.TRUE;
        Boolean showBusinessGuide = Boolean.TRUE;

        dto.setId(id);
        dto.setName(name);
        dto.setAddressCity(addressCity);
        dto.setAddressLatitude(addressLatitude);
        dto.setAddressLongitude(addressLongitude);
        dto.setAddressRegion(addressRegion);
        dto.setAddressStreet(addressStreet);
        dto.setAddressStreetNumber(addressStreetNumber);
        dto.setAddressZipCode(addressZipCode);
        dto.setContactEmail(contactEmail);
        dto.setContactMobile(contactMobile);
        dto.setContactPhone1(contactPhone1);
        dto.setContactPhone2(contactPhone2);
        dto.setContactPhone3(contactPhone3);
        dto.setContactUrl(contactUrl);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setBusinessLocation(businessLocation);
        dto.setCompany(company);
        dto.setRecdeleted(recdeleted);
        dto.setShowBusinessGuide(showBusinessGuide);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAddressCity()).isEqualTo(addressCity);
        assertThat(dto.getAddressLatitude()).isEqualTo(addressLatitude);
        assertThat(dto.getAddressLongitude()).isEqualTo(addressLongitude);
        assertThat(dto.getAddressRegion()).isEqualTo(addressRegion);
        assertThat(dto.getAddressStreet()).isEqualTo(addressStreet);
        assertThat(dto.getAddressStreetNumber()).isEqualTo(addressStreetNumber);
        assertThat(dto.getAddressZipCode()).isEqualTo(addressZipCode);
        assertThat(dto.getContactEmail()).isEqualTo(contactEmail);
        assertThat(dto.getContactMobile()).isEqualTo(contactMobile);
        assertThat(dto.getContactPhone1()).isEqualTo(contactPhone1);
        assertThat(dto.getContactPhone2()).isEqualTo(contactPhone2);
        assertThat(dto.getContactPhone3()).isEqualTo(contactPhone3);
        assertThat(dto.getContactUrl()).isEqualTo(contactUrl);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getBusinessLocation()).isEqualTo(businessLocation);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getShowBusinessGuide()).isEqualTo(showBusinessGuide);
    }

}
