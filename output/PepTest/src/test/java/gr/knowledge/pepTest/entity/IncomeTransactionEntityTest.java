package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.math.BigDecimal;

class IncomeTransactionEntityTest {

    /**
     * Tests the IncomeTransaction no-args constructor.
     */
    @Test
    void testIncomeTransactionNoArgsConstructor() {
        IncomeTransaction entity = new IncomeTransaction();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the IncomeTransaction all-args constructor.
     */
    @Test
    void testIncomeTransactionAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger chamberInTransdId = new BigInteger("1");
        String cdUse = "test-value";
        LocalDateTime dt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer isMember = 1;
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String accountCd = "test-value";
        IncomeType incomeType = new IncomeType();
        BigDecimal amount = new BigDecimal("1.00");
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger recdeleted = new BigInteger("1");
        IncomePaymentMethod incomePayMethod = new IncomePaymentMethod();
        Integer isEchamber = 1;
        String blockSer = "test-value";
        BigInteger isKratisi = new BigInteger("1");
        BigInteger chamberCompId = new BigInteger("1");
        BigInteger chamberMethod = new BigInteger("1");
        BigInteger chamberType = new BigInteger("1");

        IncomeTransaction entity = new IncomeTransaction(id, chamberId, chamberInTransdId, cdUse, dt, isMember, companyId, accountCd, incomeType, amount, lastUpdated, recdeleted, incomePayMethod, isEchamber, blockSer, isKratisi, chamberCompId, chamberMethod, chamberType);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberInTransdId()).isEqualTo(chamberInTransdId);
        assertThat(entity.getCdUse()).isEqualTo(cdUse);
        assertThat(entity.getDt()).isEqualTo(dt);
        assertThat(entity.getIsMember()).isEqualTo(isMember);
        assertThat(entity.getCompanyId()).isEqualTo(companyId);
        assertThat(entity.getAccountCd()).isEqualTo(accountCd);
        assertThat(entity.getIncomeType()).isEqualTo(incomeType);
        assertThat(entity.getAmount()).isEqualTo(amount);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getIncomePayMethod()).isEqualTo(incomePayMethod);
        assertThat(entity.getIsEchamber()).isEqualTo(isEchamber);
        assertThat(entity.getBlockSer()).isEqualTo(blockSer);
        assertThat(entity.getIsKratisi()).isEqualTo(isKratisi);
        assertThat(entity.getChamberCompId()).isEqualTo(chamberCompId);
        assertThat(entity.getChamberMethod()).isEqualTo(chamberMethod);
        assertThat(entity.getChamberType()).isEqualTo(chamberType);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testIncomeTransactionSettersAndGetters() {
        IncomeTransaction entity = new IncomeTransaction();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger chamberInTransdId = new BigInteger("1");
        String cdUse = "test-value";
        LocalDateTime dt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer isMember = 1;
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String accountCd = "test-value";
        IncomeType incomeType = new IncomeType();
        BigDecimal amount = new BigDecimal("1.00");
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger recdeleted = new BigInteger("1");
        IncomePaymentMethod incomePayMethod = new IncomePaymentMethod();
        Integer isEchamber = 1;
        String blockSer = "test-value";
        BigInteger isKratisi = new BigInteger("1");
        BigInteger chamberCompId = new BigInteger("1");
        BigInteger chamberMethod = new BigInteger("1");
        BigInteger chamberType = new BigInteger("1");

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberInTransdId(chamberInTransdId);
        entity.setCdUse(cdUse);
        entity.setDt(dt);
        entity.setIsMember(isMember);
        entity.setCompanyId(companyId);
        entity.setAccountCd(accountCd);
        entity.setIncomeType(incomeType);
        entity.setAmount(amount);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setIncomePayMethod(incomePayMethod);
        entity.setIsEchamber(isEchamber);
        entity.setBlockSer(blockSer);
        entity.setIsKratisi(isKratisi);
        entity.setChamberCompId(chamberCompId);
        entity.setChamberMethod(chamberMethod);
        entity.setChamberType(chamberType);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberInTransdId()).isEqualTo(chamberInTransdId);
        assertThat(entity.getCdUse()).isEqualTo(cdUse);
        assertThat(entity.getDt()).isEqualTo(dt);
        assertThat(entity.getIsMember()).isEqualTo(isMember);
        assertThat(entity.getCompanyId()).isEqualTo(companyId);
        assertThat(entity.getAccountCd()).isEqualTo(accountCd);
        assertThat(entity.getIncomeType()).isEqualTo(incomeType);
        assertThat(entity.getAmount()).isEqualTo(amount);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getIncomePayMethod()).isEqualTo(incomePayMethod);
        assertThat(entity.getIsEchamber()).isEqualTo(isEchamber);
        assertThat(entity.getBlockSer()).isEqualTo(blockSer);
        assertThat(entity.getIsKratisi()).isEqualTo(isKratisi);
        assertThat(entity.getChamberCompId()).isEqualTo(chamberCompId);
        assertThat(entity.getChamberMethod()).isEqualTo(chamberMethod);
        assertThat(entity.getChamberType()).isEqualTo(chamberType);
    }

}
