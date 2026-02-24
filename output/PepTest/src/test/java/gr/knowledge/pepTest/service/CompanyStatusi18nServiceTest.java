package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyStatusi18nRepository;
import gr.knowledge.pepTest.mapper.CompanyStatusi18nMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyStatusi18nServiceImpl;

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
class CompanyStatusi18nServiceTest {

    @Mock
    private CompanyStatusi18nRepository companyStatusi18nRepository;

    @Mock
    private CompanyStatusi18nMapper companyStatusi18nMapper;

    @InjectMocks
    private CompanyStatusi18nServiceImpl companyStatusi18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyStatusi18nRepository.findAll()).willReturn(List.of());
        companyStatusi18nService.getAllCompanyStatusi18n();
        verify(companyStatusi18nRepository).findAll();
        verify(companyStatusi18nMapper).toDTO(anyList());
    }
}
