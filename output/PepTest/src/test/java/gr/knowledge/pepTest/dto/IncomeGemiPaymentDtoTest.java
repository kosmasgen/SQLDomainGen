package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigInteger;

class IncomeGemiPaymentDtoTest {

    /**
     * Tests the IncomeGemiPaymentDto no-args constructor
     */
    @Test
    void testIncomeGemiPaymentDtoNoArgsConstructor() {
        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getPaymentType()).isNull();
        assertThat(dto.getSaleTs()).isNull();
        assertThat(dto.getChamberAmount()).isNull();
        assertThat(dto.getChamberAmountForCerts()).isNull();
        assertThat(dto.getChamberAmountForPostal()).isNull();
        assertThat(dto.getTotalAmountPaid()).isNull();
        assertThat(dto.getDescr()).isNull();
        assertThat(dto.getPayer()).isNull();
        assertThat(dto.getGemiPaymentId()).isNull();
        assertThat(dto.getCompanyGemiId()).isNull();
        assertThat(dto.getCoName()).isNull();
        assertThat(dto.getCompanyChamberId()).isNull();
        assertThat(dto.getPaymentMethod()).isNull();
        assertThat(dto.getRi3()).isNull();
        assertThat(dto.getSubscriptionStartDate()).isNull();
        assertThat(dto.getSubscriptionEndDate()).isNull();
        assertThat(dto.getCancelFlag()).isNull();
        assertThat(dto.getRefundTs()).isNull();
        assertThat(dto.getRemittanceDt()).isNull();
        assertThat(dto.getRemittanceAmount()).isNull();
        assertThat(dto.getRemittanceReference()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
    }

    /**
     * Tests the IncomeGemiPaymentDto all-args constructor
     */
    @Test
    void testIncomeGemiPaymentDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String paymentType = "test-value";
        LocalDateTime saleTs = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigDecimal chamberAmount = new BigDecimal("1.00");
        BigDecimal chamberAmountForCerts = new BigDecimal("1.00");
        BigDecimal chamberAmountForPostal = new BigDecimal("1.00");
        BigDecimal totalAmountPaid = new BigDecimal("1.00");
        String descr = "test-value";
        String payer = "test-value";
        BigInteger gemiPaymentId = new BigInteger("1");
        BigInteger companyGemiId = new BigInteger("1");
        String coName = "test-value";
        BigInteger companyChamberId = new BigInteger("1");
        String paymentMethod = "test-value";
        String ri3 = "test-value";
        LocalDateTime subscriptionStartDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime subscriptionEndDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger cancelFlag = new BigInteger("1");
        LocalDateTime refundTs = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime remittanceDt = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigDecimal remittanceAmount = new BigDecimal("1.00");
        String remittanceReference = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto(id, chamberId, paymentType, saleTs, chamberAmount, chamberAmountForCerts, chamberAmountForPostal, totalAmountPaid, descr, payer, gemiPaymentId, companyGemiId, coName, companyChamberId, paymentMethod, ri3, subscriptionStartDate, subscriptionEndDate, cancelFlag, refundTs, remittanceDt, remittanceAmount, remittanceReference, lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getPaymentType()).isEqualTo(paymentType);
        assertThat(dto.getSaleTs()).isEqualTo(saleTs);
        assertThat(dto.getChamberAmount()).isEqualTo(chamberAmount);
        assertThat(dto.getChamberAmountForCerts()).isEqualTo(chamberAmountForCerts);
        assertThat(dto.getChamberAmountForPostal()).isEqualTo(chamberAmountForPostal);
        assertThat(dto.getTotalAmountPaid()).isEqualTo(totalAmountPaid);
        assertThat(dto.getDescr()).isEqualTo(descr);
        assertThat(dto.getPayer()).isEqualTo(payer);
        assertThat(dto.getGemiPaymentId()).isEqualTo(gemiPaymentId);
        assertThat(dto.getCompanyGemiId()).isEqualTo(companyGemiId);
        assertThat(dto.getCoName()).isEqualTo(coName);
        assertThat(dto.getCompanyChamberId()).isEqualTo(companyChamberId);
        assertThat(dto.getPaymentMethod()).isEqualTo(paymentMethod);
        assertThat(dto.getRi3()).isEqualTo(ri3);
        assertThat(dto.getSubscriptionStartDate()).isEqualTo(subscriptionStartDate);
        assertThat(dto.getSubscriptionEndDate()).isEqualTo(subscriptionEndDate);
        assertThat(dto.getCancelFlag()).isEqualTo(cancelFlag);
        assertThat(dto.getRefundTs()).isEqualTo(refundTs);
        assertThat(dto.getRemittanceDt()).isEqualTo(remittanceDt);
        assertThat(dto.getRemittanceAmount()).isEqualTo(remittanceAmount);
        assertThat(dto.getRemittanceReference()).isEqualTo(remittanceReference);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testIncomeGemiPaymentDtoSettersAndGetters() {
        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String paymentType = "test-value";
        LocalDateTime saleTs = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigDecimal chamberAmount = new BigDecimal("1.00");
        BigDecimal chamberAmountForCerts = new BigDecimal("1.00");
        BigDecimal chamberAmountForPostal = new BigDecimal("1.00");
        BigDecimal totalAmountPaid = new BigDecimal("1.00");
        String descr = "test-value";
        String payer = "test-value";
        BigInteger gemiPaymentId = new BigInteger("1");
        BigInteger companyGemiId = new BigInteger("1");
        String coName = "test-value";
        BigInteger companyChamberId = new BigInteger("1");
        String paymentMethod = "test-value";
        String ri3 = "test-value";
        LocalDateTime subscriptionStartDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime subscriptionEndDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger cancelFlag = new BigInteger("1");
        LocalDateTime refundTs = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime remittanceDt = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigDecimal remittanceAmount = new BigDecimal("1.00");
        String remittanceReference = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setPaymentType(paymentType);
        dto.setSaleTs(saleTs);
        dto.setChamberAmount(chamberAmount);
        dto.setChamberAmountForCerts(chamberAmountForCerts);
        dto.setChamberAmountForPostal(chamberAmountForPostal);
        dto.setTotalAmountPaid(totalAmountPaid);
        dto.setDescr(descr);
        dto.setPayer(payer);
        dto.setGemiPaymentId(gemiPaymentId);
        dto.setCompanyGemiId(companyGemiId);
        dto.setCoName(coName);
        dto.setCompanyChamberId(companyChamberId);
        dto.setPaymentMethod(paymentMethod);
        dto.setRi3(ri3);
        dto.setSubscriptionStartDate(subscriptionStartDate);
        dto.setSubscriptionEndDate(subscriptionEndDate);
        dto.setCancelFlag(cancelFlag);
        dto.setRefundTs(refundTs);
        dto.setRemittanceDt(remittanceDt);
        dto.setRemittanceAmount(remittanceAmount);
        dto.setRemittanceReference(remittanceReference);
        dto.setLastUpdated(lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getPaymentType()).isEqualTo(paymentType);
        assertThat(dto.getSaleTs()).isEqualTo(saleTs);
        assertThat(dto.getChamberAmount()).isEqualTo(chamberAmount);
        assertThat(dto.getChamberAmountForCerts()).isEqualTo(chamberAmountForCerts);
        assertThat(dto.getChamberAmountForPostal()).isEqualTo(chamberAmountForPostal);
        assertThat(dto.getTotalAmountPaid()).isEqualTo(totalAmountPaid);
        assertThat(dto.getDescr()).isEqualTo(descr);
        assertThat(dto.getPayer()).isEqualTo(payer);
        assertThat(dto.getGemiPaymentId()).isEqualTo(gemiPaymentId);
        assertThat(dto.getCompanyGemiId()).isEqualTo(companyGemiId);
        assertThat(dto.getCoName()).isEqualTo(coName);
        assertThat(dto.getCompanyChamberId()).isEqualTo(companyChamberId);
        assertThat(dto.getPaymentMethod()).isEqualTo(paymentMethod);
        assertThat(dto.getRi3()).isEqualTo(ri3);
        assertThat(dto.getSubscriptionStartDate()).isEqualTo(subscriptionStartDate);
        assertThat(dto.getSubscriptionEndDate()).isEqualTo(subscriptionEndDate);
        assertThat(dto.getCancelFlag()).isEqualTo(cancelFlag);
        assertThat(dto.getRefundTs()).isEqualTo(refundTs);
        assertThat(dto.getRemittanceDt()).isEqualTo(remittanceDt);
        assertThat(dto.getRemittanceAmount()).isEqualTo(remittanceAmount);
        assertThat(dto.getRemittanceReference()).isEqualTo(remittanceReference);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
