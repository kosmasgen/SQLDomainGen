package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class StatsExpenseDtoTest {

    /**
     * Tests the StatsExpenseDto no-args constructor
     */
    @Test
    void testStatsExpenseDtoNoArgsConstructor() {
        StatsExpenseDto dto = new StatsExpenseDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getAccountSumId()).isNull();
        assertThat(dto.getCdUse()).isNull();
        assertThat(dto.getGroupDescr()).isNull();
        assertThat(dto.getMm()).isNull();
        assertThat(dto.getAmount()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
    }

    /**
     * Tests the StatsExpenseDto all-args constructor
     */
    @Test
    void testStatsExpenseDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger accountSumId = new BigInteger("1");
        String cdUse = "test-value";
        String groupDescr = "test-value";
        String mm = "test-value";
        BigDecimal amount = new BigDecimal("1.00");
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger recdeleted = new BigInteger("1");

        StatsExpenseDto dto = new StatsExpenseDto(id, chamberId, accountSumId, cdUse, groupDescr, mm, amount, lastUpdated, recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getAccountSumId()).isEqualTo(accountSumId);
        assertThat(dto.getCdUse()).isEqualTo(cdUse);
        assertThat(dto.getGroupDescr()).isEqualTo(groupDescr);
        assertThat(dto.getMm()).isEqualTo(mm);
        assertThat(dto.getAmount()).isEqualTo(amount);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testStatsExpenseDtoSettersAndGetters() {
        StatsExpenseDto dto = new StatsExpenseDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger accountSumId = new BigInteger("1");
        String cdUse = "test-value";
        String groupDescr = "test-value";
        String mm = "test-value";
        BigDecimal amount = new BigDecimal("1.00");
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger recdeleted = new BigInteger("1");

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setAccountSumId(accountSumId);
        dto.setCdUse(cdUse);
        dto.setGroupDescr(groupDescr);
        dto.setMm(mm);
        dto.setAmount(amount);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getAccountSumId()).isEqualTo(accountSumId);
        assertThat(dto.getCdUse()).isEqualTo(cdUse);
        assertThat(dto.getGroupDescr()).isEqualTo(groupDescr);
        assertThat(dto.getMm()).isEqualTo(mm);
        assertThat(dto.getAmount()).isEqualTo(amount);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
    }

}
