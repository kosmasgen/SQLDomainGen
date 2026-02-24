package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyYpArticleI18nRepository;
import gr.knowledge.pepTest.mapper.CompanyYpArticleI18nMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyYpArticleI18nServiceImpl;

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
class CompanyYpArticleI18nServiceTest {

    @Mock
    private CompanyYpArticleI18nRepository companyYpArticleI18nRepository;

    @Mock
    private CompanyYpArticleI18nMapper companyYpArticleI18nMapper;

    @InjectMocks
    private CompanyYpArticleI18nServiceImpl companyYpArticleI18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyYpArticleI18nRepository.findAll()).willReturn(List.of());
        companyYpArticleI18nService.getAllCompanyYpArticleI18n();
        verify(companyYpArticleI18nRepository).findAll();
        verify(companyYpArticleI18nMapper).toDTO(anyList());
    }
}
