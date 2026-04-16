package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.math.BigDecimal;

class IncomeTransactionDtoTest {

    /**
     * Tests the IncomeTransactionDto no-args constructor
     */
    @Test
    void testIncomeTransactionDtoNoArgsConstructor() {
        IncomeTransactionDto dto = new IncomeTransactionDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getChamberInTransdId()).isNull();
        assertThat(dto.getCdUse()).isNull();
        assertThat(dto.getDt()).isNull();
        assertThat(dto.getIsMember()).isNull();
        assertThat(dto.getCompanyId()).isNull();
        assertThat(dto.getAccountCd()).isNull();
        assertThat(dto.getIncomeType()).isNull();
        assertThat(dto.getAmount()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getIncomePayMethod()).isNull();
        assertThat(dto.getIsEchamber()).isNull();
        assertThat(dto.getBlockSer()).isNull();
        assertThat(dto.getIsKratisi()).isNull();
        assertThat(dto.getChamberCompId()).isNull();
        assertThat(dto.getChamberMethod()).isNull();
        assertThat(dto.getChamberType()).isNull();
    }

    /**
     * Tests the IncomeTransactionDto all-args constructor
     */
    @Test
    void testIncomeTransactionDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger chamberInTransdId = new BigInteger("1");
        String cdUse = "test-value";
        LocalDateTime dt = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer isMember = 1;
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String accountCd = "test-value";
        IncomeTypeDto incomeType = new IncomeTypeDto();
        BigDecimal amount = new BigDecimal("1.00");
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger recdeleted = new BigInteger("1");
        IncomePaymentMethodDto incomePayMethod = new IncomePaymentMethodDto();
        Integer isEchamber = 1;
        String blockSer = "test-value";
        BigInteger isKratisi = new BigInteger("1");
        BigInteger chamberCompId = new BigInteger("1");
        BigInteger chamberMethod = new BigInteger("1");
        BigInteger chamberType = new BigInteger("1");

        IncomeTransactionDto dto = new IncomeTransactionDto(id, chamberId, chamberInTransdId, cdUse, dt, isMember, companyId, accountCd, incomeType, amount, lastUpdated, recdeleted, incomePayMethod, isEchamber, blockSer, isKratisi, chamberCompId, chamberMethod, chamberType);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberInTransdId()).isEqualTo(chamberInTransdId);
        assertThat(dto.getCdUse()).isEqualTo(cdUse);
        assertThat(dto.getDt()).isEqualTo(dt);
        assertThat(dto.getIsMember()).isEqualTo(isMember);
        assertThat(dto.getCompanyId()).isEqualTo(companyId);
        assertThat(dto.getAccountCd()).isEqualTo(accountCd);
        assertThat(dto.getIncomeType()).isEqualTo(incomeType);
        assertThat(dto.getAmount()).isEqualTo(amount);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getIncomePayMethod()).isEqualTo(incomePayMethod);
        assertThat(dto.getIsEchamber()).isEqualTo(isEchamber);
        assertThat(dto.getBlockSer()).isEqualTo(blockSer);
        assertThat(dto.getIsKratisi()).isEqualTo(isKratisi);
        assertThat(dto.getChamberCompId()).isEqualTo(chamberCompId);
        assertThat(dto.getChamberMethod()).isEqualTo(chamberMethod);
        assertThat(dto.getChamberType()).isEqualTo(chamberType);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testIncomeTransactionDtoSettersAndGetters() {
        IncomeTransactionDto dto = new IncomeTransactionDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        BigInteger chamberInTransdId = new BigInteger("1");
        String cdUse = "test-value";
        LocalDateTime dt = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer isMember = 1;
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String accountCd = "test-value";
        IncomeTypeDto incomeType = new IncomeTypeDto();
        BigDecimal amount = new BigDecimal("1.00");
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger recdeleted = new BigInteger("1");
        IncomePaymentMethodDto incomePayMethod = new IncomePaymentMethodDto();
        Integer isEchamber = 1;
        String blockSer = "test-value";
        BigInteger isKratisi = new BigInteger("1");
        BigInteger chamberCompId = new BigInteger("1");
        BigInteger chamberMethod = new BigInteger("1");
        BigInteger chamberType = new BigInteger("1");

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setChamberInTransdId(chamberInTransdId);
        dto.setCdUse(cdUse);
        dto.setDt(dt);
        dto.setIsMember(isMember);
        dto.setCompanyId(companyId);
        dto.setAccountCd(accountCd);
        dto.setIncomeType(incomeType);
        dto.setAmount(amount);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setIncomePayMethod(incomePayMethod);
        dto.setIsEchamber(isEchamber);
        dto.setBlockSer(blockSer);
        dto.setIsKratisi(isKratisi);
        dto.setChamberCompId(chamberCompId);
        dto.setChamberMethod(chamberMethod);
        dto.setChamberType(chamberType);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberInTransdId()).isEqualTo(chamberInTransdId);
        assertThat(dto.getCdUse()).isEqualTo(cdUse);
        assertThat(dto.getDt()).isEqualTo(dt);
        assertThat(dto.getIsMember()).isEqualTo(isMember);
        assertThat(dto.getCompanyId()).isEqualTo(companyId);
        assertThat(dto.getAccountCd()).isEqualTo(accountCd);
        assertThat(dto.getIncomeType()).isEqualTo(incomeType);
        assertThat(dto.getAmount()).isEqualTo(amount);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getIncomePayMethod()).isEqualTo(incomePayMethod);
        assertThat(dto.getIsEchamber()).isEqualTo(isEchamber);
        assertThat(dto.getBlockSer()).isEqualTo(blockSer);
        assertThat(dto.getIsKratisi()).isEqualTo(isKratisi);
        assertThat(dto.getChamberCompId()).isEqualTo(chamberCompId);
        assertThat(dto.getChamberMethod()).isEqualTo(chamberMethod);
        assertThat(dto.getChamberType()).isEqualTo(chamberType);
    }

}
