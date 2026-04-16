package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class ChAppUserContactDtoTest {

    /**
     * Tests the ChAppUserContactDto no-args constructor
     */
    @Test
    void testChAppUserContactDtoNoArgsConstructor() {
        ChAppUserContactDto dto = new ChAppUserContactDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getChamberAppUser()).isNull();
        assertThat(dto.getPhone1()).isNull();
        assertThat(dto.getPhone2()).isNull();
        assertThat(dto.getMobile1()).isNull();
        assertThat(dto.getMobile2()).isNull();
        assertThat(dto.getEmail1()).isNull();
        assertThat(dto.getEmail2()).isNull();
        assertThat(dto.getUrl()).isNull();
        assertThat(dto.getZipCode()).isNull();
        assertThat(dto.getLatitude()).isNull();
        assertThat(dto.getLongitude()).isNull();
        assertThat(dto.getStreetNumber()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getListingUrl()).isNull();
        assertThat(dto.getEmail()).isNull();
        assertThat(dto.getMobile()).isNull();
    }

    /**
     * Tests the ChAppUserContactDto all-args constructor
     */
    @Test
    void testChAppUserContactDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        ChamberAppUserDto chamberAppUser = new ChamberAppUserDto();
        String phone1 = "test-value";
        String phone2 = "test-value";
        String mobile1 = "test-value";
        String mobile2 = "test-value";
        String email1 = "test-value";
        String email2 = "test-value";
        String url = "test-value";
        String zipCode = "test-value";
        String latitude = "test-value";
        String longitude = "test-value";
        String streetNumber = "test-value";
        Boolean recdeleted = Boolean.TRUE;
        String listingUrl = "test-value";
        String email = "test-value";
        String mobile = "test-value";

        ChAppUserContactDto dto = new ChAppUserContactDto(id, dateCreated, lastUpdated, chamberAppUser, phone1, phone2, mobile1, mobile2, email1, email2, url, zipCode, latitude, longitude, streetNumber, recdeleted, listingUrl, email, mobile);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getChamberAppUser()).isEqualTo(chamberAppUser);
        assertThat(dto.getPhone1()).isEqualTo(phone1);
        assertThat(dto.getPhone2()).isEqualTo(phone2);
        assertThat(dto.getMobile1()).isEqualTo(mobile1);
        assertThat(dto.getMobile2()).isEqualTo(mobile2);
        assertThat(dto.getEmail1()).isEqualTo(email1);
        assertThat(dto.getEmail2()).isEqualTo(email2);
        assertThat(dto.getUrl()).isEqualTo(url);
        assertThat(dto.getZipCode()).isEqualTo(zipCode);
        assertThat(dto.getLatitude()).isEqualTo(latitude);
        assertThat(dto.getLongitude()).isEqualTo(longitude);
        assertThat(dto.getStreetNumber()).isEqualTo(streetNumber);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getListingUrl()).isEqualTo(listingUrl);
        assertThat(dto.getEmail()).isEqualTo(email);
        assertThat(dto.getMobile()).isEqualTo(mobile);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testChAppUserContactDtoSettersAndGetters() {
        ChAppUserContactDto dto = new ChAppUserContactDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        ChamberAppUserDto chamberAppUser = new ChamberAppUserDto();
        String phone1 = "test-value";
        String phone2 = "test-value";
        String mobile1 = "test-value";
        String mobile2 = "test-value";
        String email1 = "test-value";
        String email2 = "test-value";
        String url = "test-value";
        String zipCode = "test-value";
        String latitude = "test-value";
        String longitude = "test-value";
        String streetNumber = "test-value";
        Boolean recdeleted = Boolean.TRUE;
        String listingUrl = "test-value";
        String email = "test-value";
        String mobile = "test-value";

        dto.setId(id);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setChamberAppUser(chamberAppUser);
        dto.setPhone1(phone1);
        dto.setPhone2(phone2);
        dto.setMobile1(mobile1);
        dto.setMobile2(mobile2);
        dto.setEmail1(email1);
        dto.setEmail2(email2);
        dto.setUrl(url);
        dto.setZipCode(zipCode);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setStreetNumber(streetNumber);
        dto.setRecdeleted(recdeleted);
        dto.setListingUrl(listingUrl);
        dto.setEmail(email);
        dto.setMobile(mobile);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getChamberAppUser()).isEqualTo(chamberAppUser);
        assertThat(dto.getPhone1()).isEqualTo(phone1);
        assertThat(dto.getPhone2()).isEqualTo(phone2);
        assertThat(dto.getMobile1()).isEqualTo(mobile1);
        assertThat(dto.getMobile2()).isEqualTo(mobile2);
        assertThat(dto.getEmail1()).isEqualTo(email1);
        assertThat(dto.getEmail2()).isEqualTo(email2);
        assertThat(dto.getUrl()).isEqualTo(url);
        assertThat(dto.getZipCode()).isEqualTo(zipCode);
        assertThat(dto.getLatitude()).isEqualTo(latitude);
        assertThat(dto.getLongitude()).isEqualTo(longitude);
        assertThat(dto.getStreetNumber()).isEqualTo(streetNumber);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getListingUrl()).isEqualTo(listingUrl);
        assertThat(dto.getEmail()).isEqualTo(email);
        assertThat(dto.getMobile()).isEqualTo(mobile);
    }

}
