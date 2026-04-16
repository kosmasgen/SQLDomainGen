package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class StatsExpenseEntityTest {

    /**
     * Tests the StatsExpense no-args constructor.
     */
    @Test
    void testStatsExpenseNoArgsConstructor() {
        StatsExpense entity = new StatsExpense();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the StatsExpense all-args constructor.
     */
    @Test
    void testStatsExpenseAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger accountSumId = new BigInteger("1");
        String cdUse = "test-value";
        String groupDescr = "test-value";
        String mm = "test-value";
        BigDecimal amount = new BigDecimal("1.00");
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger recdeleted = new BigInteger("1");

        StatsExpense entity = new StatsExpense(id, chamberId, accountSumId, cdUse, groupDescr, mm, amount, lastUpdated, recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getAccountSumId()).isEqualTo(accountSumId);
        assertThat(entity.getCdUse()).isEqualTo(cdUse);
        assertThat(entity.getGroupDescr()).isEqualTo(groupDescr);
        assertThat(entity.getMm()).isEqualTo(mm);
        assertThat(entity.getAmount()).isEqualTo(amount);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testStatsExpenseSettersAndGetters() {
        StatsExpense entity = new StatsExpense();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger accountSumId = new BigInteger("1");
        String cdUse = "test-value";
        String groupDescr = "test-value";
        String mm = "test-value";
        BigDecimal amount = new BigDecimal("1.00");
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger recdeleted = new BigInteger("1");

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setAccountSumId(accountSumId);
        entity.setCdUse(cdUse);
        entity.setGroupDescr(groupDescr);
        entity.setMm(mm);
        entity.setAmount(amount);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getAccountSumId()).isEqualTo(accountSumId);
        assertThat(entity.getCdUse()).isEqualTo(cdUse);
        assertThat(entity.getGroupDescr()).isEqualTo(groupDescr);
        assertThat(entity.getMm()).isEqualTo(mm);
        assertThat(entity.getAmount()).isEqualTo(amount);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
    }

}
