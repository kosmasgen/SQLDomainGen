package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.TemporaryCompany;
import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import gr.knowledge.pepTest.repository.TemporaryCompanyRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyMapper;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TemporaryCompanyServiceImplTest {

    @Mock
    private TemporaryCompanyRepository temporaryCompanyRepository;

    @Mock
    private TemporaryCompanyMapper temporaryCompanyMapper;

    @InjectMocks
    private TemporaryCompanyServiceImpl temporaryCompanyService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for TemporaryCompany.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("TemporaryCompany", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllTemporaryCompaniesIsCalled() {
        List<TemporaryCompany> entityList = List.of(createSampleTemporaryCompanyEntity(), createAnotherTemporaryCompanyEntity());
        List<TemporaryCompanyDto> dtoList = List.of(createSampleTemporaryCompanyDto(), createAnotherTemporaryCompanyDto());

        given(temporaryCompanyRepository.findAll()).willReturn(entityList);
        given(temporaryCompanyMapper.toDTOList(entityList)).willReturn(dtoList);

        List<TemporaryCompanyDto> result = temporaryCompanyService.getAllTemporaryCompanies();

        assertSame(dtoList, result);
        verify(temporaryCompanyRepository).findAll();
        verify(temporaryCompanyMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetTemporaryCompanyByIdIsCalled() {
        BigInteger id = new BigInteger("1");

        TemporaryCompany temporaryCompany = createSampleTemporaryCompanyEntity();
        TemporaryCompanyDto temporaryCompanyDto = createSampleTemporaryCompanyDto();

        given(temporaryCompanyRepository.findById(id)).willReturn(Optional.of(temporaryCompany));
        given(temporaryCompanyMapper.toDTO(temporaryCompany)).willReturn(temporaryCompanyDto);

        TemporaryCompanyDto result = temporaryCompanyService.getTemporaryCompanyById(id);

        assertSame(temporaryCompanyDto, result);
        verify(temporaryCompanyRepository).findById(id);
        verify(temporaryCompanyMapper).toDTO(temporaryCompany);
    }

    @Test
    void shouldThrowWhenGetTemporaryCompanyByIdCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyService.getTemporaryCompanyById(id));

        verify(temporaryCompanyRepository).findById(id);
        verify(temporaryCompanyMapper, never()).toDTO(any(TemporaryCompany.class));
    }

    @Test
    void shouldCreateTemporaryCompanyWhenCreateTemporaryCompanyIsCalled() {
        TemporaryCompanyDto requestDto = createSampleTemporaryCompanyDto();
        TemporaryCompany mappedEntity = createSampleTemporaryCompanyEntity();
        TemporaryCompany savedEntity = createAnotherTemporaryCompanyEntity();
        TemporaryCompanyDto responseDto = createAnotherTemporaryCompanyDto();

        given(temporaryCompanyMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(temporaryCompanyRepository.save(mappedEntity)).willReturn(savedEntity);
        given(temporaryCompanyMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyDto result = temporaryCompanyService.createTemporaryCompany(requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyMapper).toEntity(requestDto);
        verify(temporaryCompanyRepository).save(mappedEntity);
        verify(temporaryCompanyMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateTemporaryCompanyWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyDto requestDto = createPatchTemporaryCompanyDto();
        TemporaryCompany existingEntity = createSampleTemporaryCompanyEntity();
        TemporaryCompany savedEntity = createAnotherTemporaryCompanyEntity();
        TemporaryCompanyDto responseDto = createAnotherTemporaryCompanyDto();

        given(temporaryCompanyRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(temporaryCompanyRepository.save(existingEntity)).willReturn(savedEntity);
        given(temporaryCompanyMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyDto result = temporaryCompanyService.updateTemporaryCompany(id, requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyRepository).findById(id);
        verify(temporaryCompanyMapper).partialUpdate(existingEntity, requestDto);
        verify(temporaryCompanyRepository).save(existingEntity);
        verify(temporaryCompanyMapper).toDTO(savedEntity);
        verify(temporaryCompanyMapper, never()).toEntity(any(TemporaryCompanyDto.class));
    }

    @Test
    void shouldThrowWhenUpdateTemporaryCompanyCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyDto requestDto = createPatchTemporaryCompanyDto();

        given(temporaryCompanyRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyService.updateTemporaryCompany(id, requestDto));

        verify(temporaryCompanyRepository).findById(id);
        verify(temporaryCompanyMapper, never()).partialUpdate(any(), any());
        verify(temporaryCompanyRepository, never()).save(any());
    }

    @Test
    void shouldDeleteTemporaryCompanyWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompany existingEntity = createSampleTemporaryCompanyEntity();

        given(temporaryCompanyRepository.findById(id)).willReturn(Optional.of(existingEntity));

        temporaryCompanyService.deleteTemporaryCompany(id);

        verify(temporaryCompanyRepository).findById(id);
        verify(temporaryCompanyRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteTemporaryCompanyCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyService.deleteTemporaryCompany(id));

        verify(temporaryCompanyRepository).findById(id);
        verify(temporaryCompanyRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated TemporaryCompany fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompany createSampleTemporaryCompanyEntity() {
        TemporaryCompany entity = new TemporaryCompany();
        entity.setId(new BigInteger("1"));
        entity.setVersion(new BigInteger("1"));
        entity.setAddressCity("addressCity-value-1");
        entity.setAddressCountryId(new BigInteger("1"));
        entity.setAddressLatitude("addressLatitude-value-1");
        entity.setAddressLongitude("addressLongitude-value-1");
        entity.setAddressMunicipalityAlt("addressMunicipalityAlt-value-1");
        entity.setAddressMunicipalityPriId(new BigInteger("1"));
        entity.setAddressMunicipalitySecId(new BigInteger("1"));
        entity.setAddressPoBox("addressPoBox-value-1");
        entity.setAddressPrefectureId(new BigInteger("1"));
        entity.setAddressRegion("addressRegion-value-1");
        entity.setAddressStreet("addressStreet-value-1");
        entity.setAddressStreetNumber("addressStreetNumber-value-1");
        entity.setAddressZipCode("addressZipCode-value-1");
        entity.setAddressZoomLevel(new BigInteger("1"));
        entity.setAddressIndicId(new BigInteger("1"));
        entity.setAfm("afm-value-1");
        entity.setAm(new BigInteger("1"));
        entity.setArmae("armae-value-1");
        entity.setBoardDur("boardDur-value-1");
        entity.setBranchTypeId(new BigInteger("1"));
        entity.setCancelDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setCancelReasonId(new BigInteger("1"));
        entity.setCd(new BigInteger("1"));
        entity.setChamberDepartmentId(new BigInteger("1"));
        entity.setChamberGemiResponsibleId(new BigInteger("1"));
        entity.setChamberRegisteredId(new BigInteger("1"));
        entity.setCoName("coName-value-1");
        entity.setCoNameNrm("coNameNrm-value-1");
        entity.setComercRegCode(new BigInteger("1"));
        entity.setCompanyStatusId(new BigInteger("1"));
        entity.setContactEmail("contactEmail-value-1");
        entity.setContactFacebook("contactFacebook-value-1");
        entity.setContactFax("contactFax-value-1");
        entity.setContactMobile("contactMobile-value-1");
        entity.setContactPhone1("contactPhone1-value-1");
        entity.setContactPhone2("contactPhone2-value-1");
        entity.setContactPhone3("contactPhone3-value-1");
        entity.setContactPhoneArea("contactPhoneArea-value-1");
        entity.setContactTelex("contactTelex-value-1");
        entity.setContactTwitter("contactTwitter-value-1");
        entity.setContactUrl("contactUrl-value-1");
        entity.setCorporateStatusId(new BigInteger("1"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDateGemiRegistered(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDateIncorporated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDateProfessionStarted(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDateRegistered(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDisputeDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDisputeDecDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDisputeNumber("disputeNumber-value-1");
        entity.setEdra("edra-value-1");
        entity.setEmail2("email2-value-1");
        entity.setEmail3("email3-value-1");
        entity.setEmail4("email4-value-1");
        entity.setEndfirstfy(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setEuCommerce(new BigInteger("1"));
        entity.setExpManagementDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setExpireDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setFinancialYearId(new BigInteger("1"));
        entity.setFoundationDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setGemhOtherPerCd(new BigInteger("1"));
        entity.setGemiNumber("gemiNumber-value-1");
        entity.setHp("hp-value-1");
        entity.setIndefinite(new BigInteger("1"));
        entity.setLastStateChangeDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLicenceExpDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLicenceNo("licenceNo-value-1");
        entity.setMailAddress(new BigInteger("1"));
        entity.setMailName("mailName-value-1");
        entity.setMeCriteria1Id(new BigInteger("1"));
        entity.setMeCriteria2Id(new BigInteger("1"));
        entity.setMember(new BigInteger("1"));
        entity.setMemberDues(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setNationalityId(new BigInteger("1"));
        entity.setNextam(new BigInteger("1"));
        entity.setObjective("objective-value-1");
        entity.setOldam("oldam-value-1");
        entity.setPendency("pendency-value-1");
        entity.setPending(new BigInteger("1"));
        entity.setPreviousam(new BigInteger("1"));
        entity.setRecType("recType-value-1");
        entity.setRecdeleted(new BigInteger("1"));
        entity.setRegistrationTypeId(new BigInteger("1"));
        entity.setSaleTypeId(new BigInteger("1"));
        entity.setStartfirstfy(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setSubscrCat(new BigInteger("1"));
        entity.setTaxServiceId(new BigInteger("1"));
        entity.setUserIns("userIns-value-1");
        entity.setUserLastUpd("userLastUpd-value-1");
        entity.setVoteDepartmentId(new BigInteger("1"));
        entity.setVotes(new BigInteger("1"));
        entity.setManagementDur("managementDur-value-1");
        entity.setReceiveNewsletter(new BigInteger("1"));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 1));
        entity.setGemiLastStateChangeDate(LocalDate.of(2025, 1, 1));
        entity.setGemiParentGemiNumber("gemiParentGemiNumber-value-1");
        entity.setGemiMunicipalityId(new BigInteger("1"));
        entity.setGemiCity("gemiCity-value-1");
        entity.setGemiRegion("gemiRegion-value-1");
        entity.setGemiStreet("gemiStreet-value-1");
        entity.setGemiStreetNumber("gemiStreetNumber-value-1");
        entity.setGemiZipCode("gemiZipCode-value-1");
        entity.setGemiPhone1("gemiPhone1-value-1");
        entity.setGemiPhone2("gemiPhone2-value-1");
        entity.setGemiPhone3("gemiPhone3-value-1");
        entity.setGemiMobile("gemiMobile-value-1");
        entity.setGemiFax("gemiFax-value-1");
        entity.setGemiEmail("gemiEmail-value-1");
        entity.setGemiCreated(new BigInteger("1"));
        entity.setGemiId(new BigInteger("1"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        entity.setArticle("article-value-1");
        entity.setShowEmail(new BigInteger("1"));
        entity.setGemiId2(new BigInteger("1"));
        entity.setVoteDt(LocalDate.of(2025, 1, 1));
        entity.setVoteFlag('A');
        entity.setVoteEtairiaFlag('A');
        entity.setGemiDateIncorporated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setMeCriteria3Id(new BigInteger("1"));
        entity.setDateInterruption(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setCancelReasonDscr("cancelReasonDscr-value-1");
        entity.setBankruptDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setStartDtCorpStatus(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setEndDtCorpStatus(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setBankruptNumber("bankruptNumber-value-1");
        entity.setLastChangeDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setNextCompanyId(new BigInteger("1"));
        entity.setParentCompanyId(new BigInteger("1"));
        entity.setPreviousCompanyId(new BigInteger("1"));
        entity.setTransferFlag(new BigInteger("1"));
        entity.setTransferAm(new BigInteger("1"));
        entity.setProegOccupationId(new BigInteger("1"));
        entity.setProegSubscrAmnt(new BigDecimal("1.00"));
        entity.setProegSubscrYear("proegSubscrYear-value-1");
        entity.setProegSubscrDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setProegSubscrNotes("proegSubscrNotes-value-1");
        entity.setMigrCapitol(new BigInteger("1"));
        entity.setMigrCapitol2(new BigDecimal("1.00"));
        entity.setMigrManyChildrenFlag("migrManyChildrenFlag-value-1");
        entity.setMigrAmeaFlag("migrAmeaFlag-value-1");
        entity.setMigrYpokatFlag("migrYpokatFlag-value-1");
        entity.setMigrThrasherFlag("migrThrasherFlag-value-1");
        entity.setMigrLowCapitalFlag("migrLowCapitalFlag-value-1");
        entity.setMigrSendTaxServFlag("migrSendTaxServFlag-value-1");
        entity.setPrintKatastFlag(new BigInteger("1"));
        entity.setSubscrCalcDate("subscrCalcDate-value-1");
        entity.setShowBusinessGuide(new BigInteger("1"));
        entity.setField7060(1);

        return entity;
    }

    /**
     * Creates a populated TemporaryCompany fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompany createAnotherTemporaryCompanyEntity() {
        TemporaryCompany entity = new TemporaryCompany();
        entity.setId(new BigInteger("2"));
        entity.setVersion(new BigInteger("2"));
        entity.setAddressCity("addressCity-value-2");
        entity.setAddressCountryId(new BigInteger("2"));
        entity.setAddressLatitude("addressLatitude-value-2");
        entity.setAddressLongitude("addressLongitude-value-2");
        entity.setAddressMunicipalityAlt("addressMunicipalityAlt-value-2");
        entity.setAddressMunicipalityPriId(new BigInteger("2"));
        entity.setAddressMunicipalitySecId(new BigInteger("2"));
        entity.setAddressPoBox("addressPoBox-value-2");
        entity.setAddressPrefectureId(new BigInteger("2"));
        entity.setAddressRegion("addressRegion-value-2");
        entity.setAddressStreet("addressStreet-value-2");
        entity.setAddressStreetNumber("addressStreetNumber-value-2");
        entity.setAddressZipCode("addressZipCode-value-2");
        entity.setAddressZoomLevel(new BigInteger("2"));
        entity.setAddressIndicId(new BigInteger("2"));
        entity.setAfm("afm-value-2");
        entity.setAm(new BigInteger("2"));
        entity.setArmae("armae-value-2");
        entity.setBoardDur("boardDur-value-2");
        entity.setBranchTypeId(new BigInteger("2"));
        entity.setCancelDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setCancelReasonId(new BigInteger("2"));
        entity.setCd(new BigInteger("2"));
        entity.setChamberDepartmentId(new BigInteger("2"));
        entity.setChamberGemiResponsibleId(new BigInteger("2"));
        entity.setChamberRegisteredId(new BigInteger("2"));
        entity.setCoName("coName-value-2");
        entity.setCoNameNrm("coNameNrm-value-2");
        entity.setComercRegCode(new BigInteger("2"));
        entity.setCompanyStatusId(new BigInteger("2"));
        entity.setContactEmail("contactEmail-value-2");
        entity.setContactFacebook("contactFacebook-value-2");
        entity.setContactFax("contactFax-value-2");
        entity.setContactMobile("contactMobile-value-2");
        entity.setContactPhone1("contactPhone1-value-2");
        entity.setContactPhone2("contactPhone2-value-2");
        entity.setContactPhone3("contactPhone3-value-2");
        entity.setContactPhoneArea("contactPhoneArea-value-2");
        entity.setContactTelex("contactTelex-value-2");
        entity.setContactTwitter("contactTwitter-value-2");
        entity.setContactUrl("contactUrl-value-2");
        entity.setCorporateStatusId(new BigInteger("2"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDateGemiRegistered(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDateIncorporated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDateProfessionStarted(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDateRegistered(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDisputeDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDisputeDecDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDisputeNumber("disputeNumber-value-2");
        entity.setEdra("edra-value-2");
        entity.setEmail2("email2-value-2");
        entity.setEmail3("email3-value-2");
        entity.setEmail4("email4-value-2");
        entity.setEndfirstfy(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setEuCommerce(new BigInteger("2"));
        entity.setExpManagementDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setExpireDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setFinancialYearId(new BigInteger("2"));
        entity.setFoundationDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setGemhOtherPerCd(new BigInteger("2"));
        entity.setGemiNumber("gemiNumber-value-2");
        entity.setHp("hp-value-2");
        entity.setIndefinite(new BigInteger("2"));
        entity.setLastStateChangeDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLicenceExpDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLicenceNo("licenceNo-value-2");
        entity.setMailAddress(new BigInteger("2"));
        entity.setMailName("mailName-value-2");
        entity.setMeCriteria1Id(new BigInteger("2"));
        entity.setMeCriteria2Id(new BigInteger("2"));
        entity.setMember(new BigInteger("2"));
        entity.setMemberDues(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setNationalityId(new BigInteger("2"));
        entity.setNextam(new BigInteger("2"));
        entity.setObjective("objective-value-2");
        entity.setOldam("oldam-value-2");
        entity.setPendency("pendency-value-2");
        entity.setPending(new BigInteger("2"));
        entity.setPreviousam(new BigInteger("2"));
        entity.setRecType("recType-value-2");
        entity.setRecdeleted(new BigInteger("2"));
        entity.setRegistrationTypeId(new BigInteger("2"));
        entity.setSaleTypeId(new BigInteger("2"));
        entity.setStartfirstfy(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setSubscrCat(new BigInteger("2"));
        entity.setTaxServiceId(new BigInteger("2"));
        entity.setUserIns("userIns-value-2");
        entity.setUserLastUpd("userLastUpd-value-2");
        entity.setVoteDepartmentId(new BigInteger("2"));
        entity.setVotes(new BigInteger("2"));
        entity.setManagementDur("managementDur-value-2");
        entity.setReceiveNewsletter(new BigInteger("2"));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 2));
        entity.setGemiLastStateChangeDate(LocalDate.of(2025, 1, 2));
        entity.setGemiParentGemiNumber("gemiParentGemiNumber-value-2");
        entity.setGemiMunicipalityId(new BigInteger("2"));
        entity.setGemiCity("gemiCity-value-2");
        entity.setGemiRegion("gemiRegion-value-2");
        entity.setGemiStreet("gemiStreet-value-2");
        entity.setGemiStreetNumber("gemiStreetNumber-value-2");
        entity.setGemiZipCode("gemiZipCode-value-2");
        entity.setGemiPhone1("gemiPhone1-value-2");
        entity.setGemiPhone2("gemiPhone2-value-2");
        entity.setGemiPhone3("gemiPhone3-value-2");
        entity.setGemiMobile("gemiMobile-value-2");
        entity.setGemiFax("gemiFax-value-2");
        entity.setGemiEmail("gemiEmail-value-2");
        entity.setGemiCreated(new BigInteger("2"));
        entity.setGemiId(new BigInteger("2"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        entity.setArticle("article-value-2");
        entity.setShowEmail(new BigInteger("2"));
        entity.setGemiId2(new BigInteger("2"));
        entity.setVoteDt(LocalDate.of(2025, 1, 2));
        entity.setVoteFlag('B');
        entity.setVoteEtairiaFlag('B');
        entity.setGemiDateIncorporated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setMeCriteria3Id(new BigInteger("2"));
        entity.setDateInterruption(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setCancelReasonDscr("cancelReasonDscr-value-2");
        entity.setBankruptDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setStartDtCorpStatus(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setEndDtCorpStatus(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setBankruptNumber("bankruptNumber-value-2");
        entity.setLastChangeDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setNextCompanyId(new BigInteger("2"));
        entity.setParentCompanyId(new BigInteger("2"));
        entity.setPreviousCompanyId(new BigInteger("2"));
        entity.setTransferFlag(new BigInteger("2"));
        entity.setTransferAm(new BigInteger("2"));
        entity.setProegOccupationId(new BigInteger("2"));
        entity.setProegSubscrAmnt(new BigDecimal("2.00"));
        entity.setProegSubscrYear("proegSubscrYear-value-2");
        entity.setProegSubscrDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setProegSubscrNotes("proegSubscrNotes-value-2");
        entity.setMigrCapitol(new BigInteger("2"));
        entity.setMigrCapitol2(new BigDecimal("2.00"));
        entity.setMigrManyChildrenFlag("migrManyChildrenFlag-value-2");
        entity.setMigrAmeaFlag("migrAmeaFlag-value-2");
        entity.setMigrYpokatFlag("migrYpokatFlag-value-2");
        entity.setMigrThrasherFlag("migrThrasherFlag-value-2");
        entity.setMigrLowCapitalFlag("migrLowCapitalFlag-value-2");
        entity.setMigrSendTaxServFlag("migrSendTaxServFlag-value-2");
        entity.setPrintKatastFlag(new BigInteger("2"));
        entity.setSubscrCalcDate("subscrCalcDate-value-2");
        entity.setShowBusinessGuide(new BigInteger("2"));
        entity.setField7060(2);

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyDto createSampleTemporaryCompanyDto() {
        TemporaryCompanyDto dto = new TemporaryCompanyDto();
        dto.setId(new BigInteger("1"));
        dto.setVersion(new BigInteger("1"));
        dto.setAddressCity("addressCity-value-1");
        dto.setAddressCountryId(new BigInteger("1"));
        dto.setAddressLatitude("addressLatitude-value-1");
        dto.setAddressLongitude("addressLongitude-value-1");
        dto.setAddressMunicipalityAlt("addressMunicipalityAlt-value-1");
        dto.setAddressMunicipalityPriId(new BigInteger("1"));
        dto.setAddressMunicipalitySecId(new BigInteger("1"));
        dto.setAddressPoBox("addressPoBox-value-1");
        dto.setAddressPrefectureId(new BigInteger("1"));
        dto.setAddressRegion("addressRegion-value-1");
        dto.setAddressStreet("addressStreet-value-1");
        dto.setAddressStreetNumber("addressStreetNumber-value-1");
        dto.setAddressZipCode("addressZipCode-value-1");
        dto.setAddressZoomLevel(new BigInteger("1"));
        dto.setAddressIndicId(new BigInteger("1"));
        dto.setAfm("afm-value-1");
        dto.setAm(new BigInteger("1"));
        dto.setArmae("armae-value-1");
        dto.setBoardDur("boardDur-value-1");
        dto.setBranchTypeId(new BigInteger("1"));
        dto.setCancelDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setCancelReasonId(new BigInteger("1"));
        dto.setCd(new BigInteger("1"));
        dto.setChamberDepartmentId(new BigInteger("1"));
        dto.setChamberGemiResponsibleId(new BigInteger("1"));
        dto.setChamberRegisteredId(new BigInteger("1"));
        dto.setCoName("coName-value-1");
        dto.setCoNameNrm("coNameNrm-value-1");
        dto.setComercRegCode(new BigInteger("1"));
        dto.setCompanyStatusId(new BigInteger("1"));
        dto.setContactEmail("contactEmail-value-1");
        dto.setContactFacebook("contactFacebook-value-1");
        dto.setContactFax("contactFax-value-1");
        dto.setContactMobile("contactMobile-value-1");
        dto.setContactPhone1("contactPhone1-value-1");
        dto.setContactPhone2("contactPhone2-value-1");
        dto.setContactPhone3("contactPhone3-value-1");
        dto.setContactPhoneArea("contactPhoneArea-value-1");
        dto.setContactTelex("contactTelex-value-1");
        dto.setContactTwitter("contactTwitter-value-1");
        dto.setContactUrl("contactUrl-value-1");
        dto.setCorporateStatusId(new BigInteger("1"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDateGemiRegistered(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDateIncorporated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDateProfessionStarted(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDateRegistered(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDisputeDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDisputeDecDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDisputeNumber("disputeNumber-value-1");
        dto.setEdra("edra-value-1");
        dto.setEmail2("email2-value-1");
        dto.setEmail3("email3-value-1");
        dto.setEmail4("email4-value-1");
        dto.setEndfirstfy(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setEuCommerce(new BigInteger("1"));
        dto.setExpManagementDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setExpireDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setFinancialYearId(new BigInteger("1"));
        dto.setFoundationDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setGemhOtherPerCd(new BigInteger("1"));
        dto.setGemiNumber("gemiNumber-value-1");
        dto.setHp("hp-value-1");
        dto.setIndefinite(new BigInteger("1"));
        dto.setLastStateChangeDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLicenceExpDt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLicenceNo("licenceNo-value-1");
        dto.setMailAddress(new BigInteger("1"));
        dto.setMailName("mailName-value-1");
        dto.setMeCriteria1Id(new BigInteger("1"));
        dto.setMeCriteria2Id(new BigInteger("1"));
        dto.setMember(new BigInteger("1"));
        dto.setMemberDues(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setNationalityId(new BigInteger("1"));
        dto.setNextam(new BigInteger("1"));
        dto.setObjective("objective-value-1");
        dto.setOldam("oldam-value-1");
        dto.setPendency("pendency-value-1");
        dto.setPending(new BigInteger("1"));
        dto.setPreviousam(new BigInteger("1"));
        dto.setRecType("recType-value-1");
        dto.setRecdeleted(new BigInteger("1"));
        dto.setRegistrationTypeId(new BigInteger("1"));
        dto.setSaleTypeId(new BigInteger("1"));
        dto.setStartfirstfy(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setSubscrCat(new BigInteger("1"));
        dto.setTaxServiceId(new BigInteger("1"));
        dto.setUserIns("userIns-value-1");
        dto.setUserLastUpd("userLastUpd-value-1");
        dto.setVoteDepartmentId(new BigInteger("1"));
        dto.setVotes(new BigInteger("1"));
        dto.setManagementDur("managementDur-value-1");
        dto.setReceiveNewsletter(new BigInteger("1"));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 1));
        dto.setGemiLastStateChangeDate(LocalDate.of(2025, 1, 1));
        dto.setGemiParentGemiNumber("gemiParentGemiNumber-value-1");
        dto.setGemiMunicipalityId(new BigInteger("1"));
        dto.setGemiCity("gemiCity-value-1");
        dto.setGemiRegion("gemiRegion-value-1");
        dto.setGemiStreet("gemiStreet-value-1");
        dto.setGemiStreetNumber("gemiStreetNumber-value-1");
        dto.setGemiZipCode("gemiZipCode-value-1");
        dto.setGemiPhone1("gemiPhone1-value-1");
        dto.setGemiPhone2("gemiPhone2-value-1");
        dto.setGemiPhone3("gemiPhone3-value-1");
        dto.setGemiMobile("gemiMobile-value-1");
        dto.setGemiFax("gemiFax-value-1");
        dto.setGemiEmail("gemiEmail-value-1");
        dto.setGemiCreated(new BigInteger("1"));
        dto.setGemiId(new BigInteger("1"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        dto.setArticle("article-value-1");
        dto.setShowEmail(new BigInteger("1"));
        dto.setGemiId2(new BigInteger("1"));
        dto.setVoteDt(LocalDate.of(2025, 1, 1));
        dto.setVoteFlag('A');
        dto.setVoteEtairiaFlag('A');
        dto.setGemiDateIncorporated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setMeCriteria3Id(new BigInteger("1"));
        dto.setDateInterruption(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setCancelReasonDscr("cancelReasonDscr-value-1");
        dto.setBankruptDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setStartDtCorpStatus(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setEndDtCorpStatus(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setBankruptNumber("bankruptNumber-value-1");
        dto.setLastChangeDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setNextCompanyId(new BigInteger("1"));
        dto.setParentCompanyId(new BigInteger("1"));
        dto.setPreviousCompanyId(new BigInteger("1"));
        dto.setTransferFlag(new BigInteger("1"));
        dto.setTransferAm(new BigInteger("1"));
        dto.setProegOccupationId(new BigInteger("1"));
        dto.setProegSubscrAmnt(new BigDecimal("1.00"));
        dto.setProegSubscrYear("proegSubscrYear-value-1");
        dto.setProegSubscrDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setProegSubscrNotes("proegSubscrNotes-value-1");
        dto.setMigrCapitol(new BigInteger("1"));
        dto.setMigrCapitol2(new BigDecimal("1.00"));
        dto.setMigrManyChildrenFlag("migrManyChildrenFlag-value-1");
        dto.setMigrAmeaFlag("migrAmeaFlag-value-1");
        dto.setMigrYpokatFlag("migrYpokatFlag-value-1");
        dto.setMigrThrasherFlag("migrThrasherFlag-value-1");
        dto.setMigrLowCapitalFlag("migrLowCapitalFlag-value-1");
        dto.setMigrSendTaxServFlag("migrSendTaxServFlag-value-1");
        dto.setPrintKatastFlag(new BigInteger("1"));
        dto.setSubscrCalcDate("subscrCalcDate-value-1");
        dto.setShowBusinessGuide(new BigInteger("1"));
        dto.setField7060(1);

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyDto createAnotherTemporaryCompanyDto() {
        TemporaryCompanyDto dto = new TemporaryCompanyDto();
        dto.setId(new BigInteger("2"));
        dto.setVersion(new BigInteger("2"));
        dto.setAddressCity("addressCity-value-2");
        dto.setAddressCountryId(new BigInteger("2"));
        dto.setAddressLatitude("addressLatitude-value-2");
        dto.setAddressLongitude("addressLongitude-value-2");
        dto.setAddressMunicipalityAlt("addressMunicipalityAlt-value-2");
        dto.setAddressMunicipalityPriId(new BigInteger("2"));
        dto.setAddressMunicipalitySecId(new BigInteger("2"));
        dto.setAddressPoBox("addressPoBox-value-2");
        dto.setAddressPrefectureId(new BigInteger("2"));
        dto.setAddressRegion("addressRegion-value-2");
        dto.setAddressStreet("addressStreet-value-2");
        dto.setAddressStreetNumber("addressStreetNumber-value-2");
        dto.setAddressZipCode("addressZipCode-value-2");
        dto.setAddressZoomLevel(new BigInteger("2"));
        dto.setAddressIndicId(new BigInteger("2"));
        dto.setAfm("afm-value-2");
        dto.setAm(new BigInteger("2"));
        dto.setArmae("armae-value-2");
        dto.setBoardDur("boardDur-value-2");
        dto.setBranchTypeId(new BigInteger("2"));
        dto.setCancelDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setCancelReasonId(new BigInteger("2"));
        dto.setCd(new BigInteger("2"));
        dto.setChamberDepartmentId(new BigInteger("2"));
        dto.setChamberGemiResponsibleId(new BigInteger("2"));
        dto.setChamberRegisteredId(new BigInteger("2"));
        dto.setCoName("coName-value-2");
        dto.setCoNameNrm("coNameNrm-value-2");
        dto.setComercRegCode(new BigInteger("2"));
        dto.setCompanyStatusId(new BigInteger("2"));
        dto.setContactEmail("contactEmail-value-2");
        dto.setContactFacebook("contactFacebook-value-2");
        dto.setContactFax("contactFax-value-2");
        dto.setContactMobile("contactMobile-value-2");
        dto.setContactPhone1("contactPhone1-value-2");
        dto.setContactPhone2("contactPhone2-value-2");
        dto.setContactPhone3("contactPhone3-value-2");
        dto.setContactPhoneArea("contactPhoneArea-value-2");
        dto.setContactTelex("contactTelex-value-2");
        dto.setContactTwitter("contactTwitter-value-2");
        dto.setContactUrl("contactUrl-value-2");
        dto.setCorporateStatusId(new BigInteger("2"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDateGemiRegistered(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDateIncorporated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDateProfessionStarted(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDateRegistered(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDisputeDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDisputeDecDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDisputeNumber("disputeNumber-value-2");
        dto.setEdra("edra-value-2");
        dto.setEmail2("email2-value-2");
        dto.setEmail3("email3-value-2");
        dto.setEmail4("email4-value-2");
        dto.setEndfirstfy(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setEuCommerce(new BigInteger("2"));
        dto.setExpManagementDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setExpireDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setFinancialYearId(new BigInteger("2"));
        dto.setFoundationDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setGemhOtherPerCd(new BigInteger("2"));
        dto.setGemiNumber("gemiNumber-value-2");
        dto.setHp("hp-value-2");
        dto.setIndefinite(new BigInteger("2"));
        dto.setLastStateChangeDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLicenceExpDt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLicenceNo("licenceNo-value-2");
        dto.setMailAddress(new BigInteger("2"));
        dto.setMailName("mailName-value-2");
        dto.setMeCriteria1Id(new BigInteger("2"));
        dto.setMeCriteria2Id(new BigInteger("2"));
        dto.setMember(new BigInteger("2"));
        dto.setMemberDues(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setNationalityId(new BigInteger("2"));
        dto.setNextam(new BigInteger("2"));
        dto.setObjective("objective-value-2");
        dto.setOldam("oldam-value-2");
        dto.setPendency("pendency-value-2");
        dto.setPending(new BigInteger("2"));
        dto.setPreviousam(new BigInteger("2"));
        dto.setRecType("recType-value-2");
        dto.setRecdeleted(new BigInteger("2"));
        dto.setRegistrationTypeId(new BigInteger("2"));
        dto.setSaleTypeId(new BigInteger("2"));
        dto.setStartfirstfy(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setSubscrCat(new BigInteger("2"));
        dto.setTaxServiceId(new BigInteger("2"));
        dto.setUserIns("userIns-value-2");
        dto.setUserLastUpd("userLastUpd-value-2");
        dto.setVoteDepartmentId(new BigInteger("2"));
        dto.setVotes(new BigInteger("2"));
        dto.setManagementDur("managementDur-value-2");
        dto.setReceiveNewsletter(new BigInteger("2"));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 2));
        dto.setGemiLastStateChangeDate(LocalDate.of(2025, 1, 2));
        dto.setGemiParentGemiNumber("gemiParentGemiNumber-value-2");
        dto.setGemiMunicipalityId(new BigInteger("2"));
        dto.setGemiCity("gemiCity-value-2");
        dto.setGemiRegion("gemiRegion-value-2");
        dto.setGemiStreet("gemiStreet-value-2");
        dto.setGemiStreetNumber("gemiStreetNumber-value-2");
        dto.setGemiZipCode("gemiZipCode-value-2");
        dto.setGemiPhone1("gemiPhone1-value-2");
        dto.setGemiPhone2("gemiPhone2-value-2");
        dto.setGemiPhone3("gemiPhone3-value-2");
        dto.setGemiMobile("gemiMobile-value-2");
        dto.setGemiFax("gemiFax-value-2");
        dto.setGemiEmail("gemiEmail-value-2");
        dto.setGemiCreated(new BigInteger("2"));
        dto.setGemiId(new BigInteger("2"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        dto.setArticle("article-value-2");
        dto.setShowEmail(new BigInteger("2"));
        dto.setGemiId2(new BigInteger("2"));
        dto.setVoteDt(LocalDate.of(2025, 1, 2));
        dto.setVoteFlag('B');
        dto.setVoteEtairiaFlag('B');
        dto.setGemiDateIncorporated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setMeCriteria3Id(new BigInteger("2"));
        dto.setDateInterruption(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setCancelReasonDscr("cancelReasonDscr-value-2");
        dto.setBankruptDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setStartDtCorpStatus(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setEndDtCorpStatus(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setBankruptNumber("bankruptNumber-value-2");
        dto.setLastChangeDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setNextCompanyId(new BigInteger("2"));
        dto.setParentCompanyId(new BigInteger("2"));
        dto.setPreviousCompanyId(new BigInteger("2"));
        dto.setTransferFlag(new BigInteger("2"));
        dto.setTransferAm(new BigInteger("2"));
        dto.setProegOccupationId(new BigInteger("2"));
        dto.setProegSubscrAmnt(new BigDecimal("2.00"));
        dto.setProegSubscrYear("proegSubscrYear-value-2");
        dto.setProegSubscrDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setProegSubscrNotes("proegSubscrNotes-value-2");
        dto.setMigrCapitol(new BigInteger("2"));
        dto.setMigrCapitol2(new BigDecimal("2.00"));
        dto.setMigrManyChildrenFlag("migrManyChildrenFlag-value-2");
        dto.setMigrAmeaFlag("migrAmeaFlag-value-2");
        dto.setMigrYpokatFlag("migrYpokatFlag-value-2");
        dto.setMigrThrasherFlag("migrThrasherFlag-value-2");
        dto.setMigrLowCapitalFlag("migrLowCapitalFlag-value-2");
        dto.setMigrSendTaxServFlag("migrSendTaxServFlag-value-2");
        dto.setPrintKatastFlag(new BigInteger("2"));
        dto.setSubscrCalcDate("subscrCalcDate-value-2");
        dto.setShowBusinessGuide(new BigInteger("2"));
        dto.setField7060(2);

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyDto createPatchTemporaryCompanyDto() {
        TemporaryCompanyDto dto = new TemporaryCompanyDto();
        dto.setVersion(new BigInteger("3"));
        dto.setAddressCity("addressCity-value-3");
        dto.setAddressCountryId(new BigInteger("3"));
        dto.setAddressLatitude("addressLatitude-value-3");
        dto.setAddressLongitude("addressLongitude-value-3");
        dto.setAddressMunicipalityAlt("addressMunicipalityAlt-value-3");
        dto.setAddressMunicipalityPriId(new BigInteger("3"));
        dto.setAddressMunicipalitySecId(new BigInteger("3"));
        dto.setAddressPoBox("addressPoBox-value-3");
        dto.setAddressPrefectureId(new BigInteger("3"));
        dto.setAddressRegion("addressRegion-value-3");
        dto.setAddressStreet("addressStreet-value-3");
        dto.setAddressStreetNumber("addressStreetNumber-value-3");
        dto.setAddressZipCode("addressZipCode-value-3");
        dto.setAddressZoomLevel(new BigInteger("3"));
        dto.setAddressIndicId(new BigInteger("3"));
        dto.setAfm("afm-value-3");
        dto.setAm(new BigInteger("3"));
        dto.setArmae("armae-value-3");
        dto.setBoardDur("boardDur-value-3");
        dto.setBranchTypeId(new BigInteger("3"));
        dto.setCancelDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setCancelReasonId(new BigInteger("3"));
        dto.setCd(new BigInteger("3"));
        dto.setChamberDepartmentId(new BigInteger("3"));
        dto.setChamberGemiResponsibleId(new BigInteger("3"));
        dto.setChamberRegisteredId(new BigInteger("3"));
        dto.setCoName("coName-value-3");
        dto.setCoNameNrm("coNameNrm-value-3");
        dto.setComercRegCode(new BigInteger("3"));
        dto.setCompanyStatusId(new BigInteger("3"));
        dto.setContactEmail("contactEmail-value-3");
        dto.setContactFacebook("contactFacebook-value-3");
        dto.setContactFax("contactFax-value-3");
        dto.setContactMobile("contactMobile-value-3");
        dto.setContactPhone1("contactPhone1-value-3");
        dto.setContactPhone2("contactPhone2-value-3");
        dto.setContactPhone3("contactPhone3-value-3");
        dto.setContactPhoneArea("contactPhoneArea-value-3");
        dto.setContactTelex("contactTelex-value-3");
        dto.setContactTwitter("contactTwitter-value-3");
        dto.setContactUrl("contactUrl-value-3");
        dto.setCorporateStatusId(new BigInteger("3"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDateGemiRegistered(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDateIncorporated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDateProfessionStarted(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDateRegistered(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDisputeDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDisputeDecDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDisputeNumber("disputeNumber-value-3");
        dto.setEdra("edra-value-3");
        dto.setEmail2("email2-value-3");
        dto.setEmail3("email3-value-3");
        dto.setEmail4("email4-value-3");
        dto.setEndfirstfy(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setEuCommerce(new BigInteger("3"));
        dto.setExpManagementDt(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setExpireDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setFinancialYearId(new BigInteger("3"));
        dto.setFoundationDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setGemhOtherPerCd(new BigInteger("3"));
        dto.setGemiNumber("gemiNumber-value-3");
        dto.setHp("hp-value-3");
        dto.setIndefinite(new BigInteger("3"));
        dto.setLastStateChangeDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLicenceExpDt(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLicenceNo("licenceNo-value-3");
        dto.setMailAddress(new BigInteger("3"));
        dto.setMailName("mailName-value-3");
        dto.setMeCriteria1Id(new BigInteger("3"));
        dto.setMeCriteria2Id(new BigInteger("3"));
        dto.setMember(new BigInteger("3"));
        dto.setMemberDues(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setNationalityId(new BigInteger("3"));
        dto.setNextam(new BigInteger("3"));
        dto.setObjective("objective-value-3");
        dto.setOldam("oldam-value-3");
        dto.setPendency("pendency-value-3");
        dto.setPending(new BigInteger("3"));
        dto.setPreviousam(new BigInteger("3"));
        dto.setRecType("recType-value-3");
        dto.setRecdeleted(new BigInteger("3"));
        dto.setRegistrationTypeId(new BigInteger("3"));
        dto.setSaleTypeId(new BigInteger("3"));
        dto.setStartfirstfy(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setSubscrCat(new BigInteger("3"));
        dto.setTaxServiceId(new BigInteger("3"));
        dto.setUserIns("userIns-value-3");
        dto.setUserLastUpd("userLastUpd-value-3");
        dto.setVoteDepartmentId(new BigInteger("3"));
        dto.setVotes(new BigInteger("3"));
        dto.setManagementDur("managementDur-value-3");
        dto.setReceiveNewsletter(new BigInteger("3"));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 3));
        dto.setGemiLastStateChangeDate(LocalDate.of(2025, 1, 3));
        dto.setGemiParentGemiNumber("gemiParentGemiNumber-value-3");
        dto.setGemiMunicipalityId(new BigInteger("3"));
        dto.setGemiCity("gemiCity-value-3");
        dto.setGemiRegion("gemiRegion-value-3");
        dto.setGemiStreet("gemiStreet-value-3");
        dto.setGemiStreetNumber("gemiStreetNumber-value-3");
        dto.setGemiZipCode("gemiZipCode-value-3");
        dto.setGemiPhone1("gemiPhone1-value-3");
        dto.setGemiPhone2("gemiPhone2-value-3");
        dto.setGemiPhone3("gemiPhone3-value-3");
        dto.setGemiMobile("gemiMobile-value-3");
        dto.setGemiFax("gemiFax-value-3");
        dto.setGemiEmail("gemiEmail-value-3");
        dto.setGemiCreated(new BigInteger("3"));
        dto.setGemiId(new BigInteger("3"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 3));
        dto.setArticle("article-value-3");
        dto.setShowEmail(new BigInteger("3"));
        dto.setGemiId2(new BigInteger("3"));
        dto.setVoteDt(LocalDate.of(2025, 1, 3));
        dto.setVoteFlag('A');
        dto.setVoteEtairiaFlag('A');
        dto.setGemiDateIncorporated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setMeCriteria3Id(new BigInteger("3"));
        dto.setDateInterruption(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setCancelReasonDscr("cancelReasonDscr-value-3");
        dto.setBankruptDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setStartDtCorpStatus(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setEndDtCorpStatus(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setBankruptNumber("bankruptNumber-value-3");
        dto.setLastChangeDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setNextCompanyId(new BigInteger("3"));
        dto.setParentCompanyId(new BigInteger("3"));
        dto.setPreviousCompanyId(new BigInteger("3"));
        dto.setTransferFlag(new BigInteger("3"));
        dto.setTransferAm(new BigInteger("3"));
        dto.setProegOccupationId(new BigInteger("3"));
        dto.setProegSubscrAmnt(new BigDecimal("3.00"));
        dto.setProegSubscrYear("proegSubscrYear-value-3");
        dto.setProegSubscrDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setProegSubscrNotes("proegSubscrNotes-value-3");
        dto.setMigrCapitol(new BigInteger("3"));
        dto.setMigrCapitol2(new BigDecimal("3.00"));
        dto.setMigrManyChildrenFlag("migrManyChildrenFlag-value-3");
        dto.setMigrAmeaFlag("migrAmeaFlag-value-3");
        dto.setMigrYpokatFlag("migrYpokatFlag-value-3");
        dto.setMigrThrasherFlag("migrThrasherFlag-value-3");
        dto.setMigrLowCapitalFlag("migrLowCapitalFlag-value-3");
        dto.setMigrSendTaxServFlag("migrSendTaxServFlag-value-3");
        dto.setPrintKatastFlag(new BigInteger("3"));
        dto.setSubscrCalcDate("subscrCalcDate-value-3");
        dto.setShowBusinessGuide(new BigInteger("3"));
        dto.setField7060(3);

        return dto;
    }

}
