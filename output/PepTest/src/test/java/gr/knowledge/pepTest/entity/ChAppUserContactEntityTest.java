package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class ChAppUserContactEntityTest {

    /**
     * Tests the ChAppUserContact no-args constructor.
     */
    @Test
    void testChAppUserContactNoArgsConstructor() {
        ChAppUserContact entity = new ChAppUserContact();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ChAppUserContact all-args constructor.
     */
    @Test
    void testChAppUserContactAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        ChamberAppUser chamberAppUser = new ChamberAppUser();
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

        ChAppUserContact entity = new ChAppUserContact(id, dateCreated, lastUpdated, chamberAppUser, phone1, phone2, mobile1, mobile2, email1, email2, url, zipCode, latitude, longitude, streetNumber, recdeleted, listingUrl, email, mobile);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getChamberAppUser()).isEqualTo(chamberAppUser);
        assertThat(entity.getPhone1()).isEqualTo(phone1);
        assertThat(entity.getPhone2()).isEqualTo(phone2);
        assertThat(entity.getMobile1()).isEqualTo(mobile1);
        assertThat(entity.getMobile2()).isEqualTo(mobile2);
        assertThat(entity.getEmail1()).isEqualTo(email1);
        assertThat(entity.getEmail2()).isEqualTo(email2);
        assertThat(entity.getUrl()).isEqualTo(url);
        assertThat(entity.getZipCode()).isEqualTo(zipCode);
        assertThat(entity.getLatitude()).isEqualTo(latitude);
        assertThat(entity.getLongitude()).isEqualTo(longitude);
        assertThat(entity.getStreetNumber()).isEqualTo(streetNumber);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getListingUrl()).isEqualTo(listingUrl);
        assertThat(entity.getEmail()).isEqualTo(email);
        assertThat(entity.getMobile()).isEqualTo(mobile);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testChAppUserContactSettersAndGetters() {
        ChAppUserContact entity = new ChAppUserContact();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        ChamberAppUser chamberAppUser = new ChamberAppUser();
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

        entity.setId(id);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setChamberAppUser(chamberAppUser);
        entity.setPhone1(phone1);
        entity.setPhone2(phone2);
        entity.setMobile1(mobile1);
        entity.setMobile2(mobile2);
        entity.setEmail1(email1);
        entity.setEmail2(email2);
        entity.setUrl(url);
        entity.setZipCode(zipCode);
        entity.setLatitude(latitude);
        entity.setLongitude(longitude);
        entity.setStreetNumber(streetNumber);
        entity.setRecdeleted(recdeleted);
        entity.setListingUrl(listingUrl);
        entity.setEmail(email);
        entity.setMobile(mobile);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getChamberAppUser()).isEqualTo(chamberAppUser);
        assertThat(entity.getPhone1()).isEqualTo(phone1);
        assertThat(entity.getPhone2()).isEqualTo(phone2);
        assertThat(entity.getMobile1()).isEqualTo(mobile1);
        assertThat(entity.getMobile2()).isEqualTo(mobile2);
        assertThat(entity.getEmail1()).isEqualTo(email1);
        assertThat(entity.getEmail2()).isEqualTo(email2);
        assertThat(entity.getUrl()).isEqualTo(url);
        assertThat(entity.getZipCode()).isEqualTo(zipCode);
        assertThat(entity.getLatitude()).isEqualTo(latitude);
        assertThat(entity.getLongitude()).isEqualTo(longitude);
        assertThat(entity.getStreetNumber()).isEqualTo(streetNumber);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getListingUrl()).isEqualTo(listingUrl);
        assertThat(entity.getEmail()).isEqualTo(email);
        assertThat(entity.getMobile()).isEqualTo(mobile);
    }

}
