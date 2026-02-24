package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyBgCooperationI18nRepository;
import gr.knowledge.pepTest.mapper.CompanyBgCooperationI18nMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyBgCooperationI18nServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CompanyBgCooperationI18nServiceTest {

    @Mock
    private CompanyBgCooperationI18nRepository companyBgCooperationI18nRepository;

    @Mock
    private CompanyBgCooperationI18nMapper companyBgCooperationI18nMapper;

    @InjectMocks
    private CompanyBgCooperationI18nServiceImpl companyBgCooperationI18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyBgCooperationI18nRepository.findAll()).willReturn(List.of());
        companyBgCooperationI18nService.getAllCompanyBgCooperationI18n();
        verify(companyBgCooperationI18nRepository).findAll();
        verify(companyBgCooperationI18nMapper).toDTO(anyList());
    }
}
