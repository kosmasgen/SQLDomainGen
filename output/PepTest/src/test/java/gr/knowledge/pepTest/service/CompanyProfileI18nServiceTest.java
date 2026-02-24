package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyProfileI18nRepository;
import gr.knowledge.pepTest.mapper.CompanyProfileI18nMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyProfileI18nServiceImpl;

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
class CompanyProfileI18nServiceTest {

    @Mock
    private CompanyProfileI18nRepository companyProfileI18nRepository;

    @Mock
    private CompanyProfileI18nMapper companyProfileI18nMapper;

    @InjectMocks
    private CompanyProfileI18nServiceImpl companyProfileI18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyProfileI18nRepository.findAll()).willReturn(List.of());
        companyProfileI18nService.getAllCompanyProfileI18n();
        verify(companyProfileI18nRepository).findAll();
        verify(companyProfileI18nMapper).toDTO(anyList());
    }
}
