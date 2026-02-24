package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyTitlei18nRepository;
import gr.knowledge.pepTest.mapper.CompanyTitlei18nMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyTitlei18nServiceImpl;

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
class CompanyTitlei18nServiceTest {

    @Mock
    private CompanyTitlei18nRepository companyTitlei18nRepository;

    @Mock
    private CompanyTitlei18nMapper companyTitlei18nMapper;

    @InjectMocks
    private CompanyTitlei18nServiceImpl companyTitlei18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyTitlei18nRepository.findAll()).willReturn(List.of());
        companyTitlei18nService.getAllCompanyTitlei18n();
        verify(companyTitlei18nRepository).findAll();
        verify(companyTitlei18nMapper).toDTO(anyList());
    }
}
