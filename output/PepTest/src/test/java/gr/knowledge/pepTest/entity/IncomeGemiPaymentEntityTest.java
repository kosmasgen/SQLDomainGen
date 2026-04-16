package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigInteger;

class IncomeGemiPaymentEntityTest {

    /**
     * Tests the IncomeGemiPayment no-args constructor.
     */
    @Test
    void testIncomeGemiPaymentNoArgsConstructor() {
        IncomeGemiPayment entity = new IncomeGemiPayment();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the IncomeGemiPayment all-args constructor.
     */
    @Test
    void testIncomeGemiPaymentAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String paymentType = "test-value";
        LocalDateTime saleTs = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
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
        LocalDateTime subscriptionStartDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime subscriptionEndDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger cancelFlag = new BigInteger("1");
        LocalDateTime refundTs = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime remittanceDt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigDecimal remittanceAmount = new BigDecimal("1.00");
        String remittanceReference = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        IncomeGemiPayment entity = new IncomeGemiPayment(id, chamberId, paymentType, saleTs, chamberAmount, chamberAmountForCerts, chamberAmountForPostal, totalAmountPaid, descr, payer, gemiPaymentId, companyGemiId, coName, companyChamberId, paymentMethod, ri3, subscriptionStartDate, subscriptionEndDate, cancelFlag, refundTs, remittanceDt, remittanceAmount, remittanceReference, lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getPaymentType()).isEqualTo(paymentType);
        assertThat(entity.getSaleTs()).isEqualTo(saleTs);
        assertThat(entity.getChamberAmount()).isEqualTo(chamberAmount);
        assertThat(entity.getChamberAmountForCerts()).isEqualTo(chamberAmountForCerts);
        assertThat(entity.getChamberAmountForPostal()).isEqualTo(chamberAmountForPostal);
        assertThat(entity.getTotalAmountPaid()).isEqualTo(totalAmountPaid);
        assertThat(entity.getDescr()).isEqualTo(descr);
        assertThat(entity.getPayer()).isEqualTo(payer);
        assertThat(entity.getGemiPaymentId()).isEqualTo(gemiPaymentId);
        assertThat(entity.getCompanyGemiId()).isEqualTo(companyGemiId);
        assertThat(entity.getCoName()).isEqualTo(coName);
        assertThat(entity.getCompanyChamberId()).isEqualTo(companyChamberId);
        assertThat(entity.getPaymentMethod()).isEqualTo(paymentMethod);
        assertThat(entity.getRi3()).isEqualTo(ri3);
        assertThat(entity.getSubscriptionStartDate()).isEqualTo(subscriptionStartDate);
        assertThat(entity.getSubscriptionEndDate()).isEqualTo(subscriptionEndDate);
        assertThat(entity.getCancelFlag()).isEqualTo(cancelFlag);
        assertThat(entity.getRefundTs()).isEqualTo(refundTs);
        assertThat(entity.getRemittanceDt()).isEqualTo(remittanceDt);
        assertThat(entity.getRemittanceAmount()).isEqualTo(remittanceAmount);
        assertThat(entity.getRemittanceReference()).isEqualTo(remittanceReference);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testIncomeGemiPaymentSettersAndGetters() {
        IncomeGemiPayment entity = new IncomeGemiPayment();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String paymentType = "test-value";
        LocalDateTime saleTs = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
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
        LocalDateTime subscriptionStartDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime subscriptionEndDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger cancelFlag = new BigInteger("1");
        LocalDateTime refundTs = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime remittanceDt = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigDecimal remittanceAmount = new BigDecimal("1.00");
        String remittanceReference = "test-value";
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setPaymentType(paymentType);
        entity.setSaleTs(saleTs);
        entity.setChamberAmount(chamberAmount);
        entity.setChamberAmountForCerts(chamberAmountForCerts);
        entity.setChamberAmountForPostal(chamberAmountForPostal);
        entity.setTotalAmountPaid(totalAmountPaid);
        entity.setDescr(descr);
        entity.setPayer(payer);
        entity.setGemiPaymentId(gemiPaymentId);
        entity.setCompanyGemiId(companyGemiId);
        entity.setCoName(coName);
        entity.setCompanyChamberId(companyChamberId);
        entity.setPaymentMethod(paymentMethod);
        entity.setRi3(ri3);
        entity.setSubscriptionStartDate(subscriptionStartDate);
        entity.setSubscriptionEndDate(subscriptionEndDate);
        entity.setCancelFlag(cancelFlag);
        entity.setRefundTs(refundTs);
        entity.setRemittanceDt(remittanceDt);
        entity.setRemittanceAmount(remittanceAmount);
        entity.setRemittanceReference(remittanceReference);
        entity.setLastUpdated(lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getPaymentType()).isEqualTo(paymentType);
        assertThat(entity.getSaleTs()).isEqualTo(saleTs);
        assertThat(entity.getChamberAmount()).isEqualTo(chamberAmount);
        assertThat(entity.getChamberAmountForCerts()).isEqualTo(chamberAmountForCerts);
        assertThat(entity.getChamberAmountForPostal()).isEqualTo(chamberAmountForPostal);
        assertThat(entity.getTotalAmountPaid()).isEqualTo(totalAmountPaid);
        assertThat(entity.getDescr()).isEqualTo(descr);
        assertThat(entity.getPayer()).isEqualTo(payer);
        assertThat(entity.getGemiPaymentId()).isEqualTo(gemiPaymentId);
        assertThat(entity.getCompanyGemiId()).isEqualTo(companyGemiId);
        assertThat(entity.getCoName()).isEqualTo(coName);
        assertThat(entity.getCompanyChamberId()).isEqualTo(companyChamberId);
        assertThat(entity.getPaymentMethod()).isEqualTo(paymentMethod);
        assertThat(entity.getRi3()).isEqualTo(ri3);
        assertThat(entity.getSubscriptionStartDate()).isEqualTo(subscriptionStartDate);
        assertThat(entity.getSubscriptionEndDate()).isEqualTo(subscriptionEndDate);
        assertThat(entity.getCancelFlag()).isEqualTo(cancelFlag);
        assertThat(entity.getRefundTs()).isEqualTo(refundTs);
        assertThat(entity.getRemittanceDt()).isEqualTo(remittanceDt);
        assertThat(entity.getRemittanceAmount()).isEqualTo(remittanceAmount);
        assertThat(entity.getRemittanceReference()).isEqualTo(remittanceReference);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
