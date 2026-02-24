package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyYpArticleRepository;
import gr.knowledge.pepTest.mapper.CompanyYpArticleMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyYpArticleServiceImpl;

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
class CompanyYpArticleServiceTest {

    @Mock
    private CompanyYpArticleRepository companyYpArticleRepository;

    @Mock
    private CompanyYpArticleMapper companyYpArticleMapper;

    @InjectMocks
    private CompanyYpArticleServiceImpl companyYpArticleService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyYpArticleRepository.findAll()).willReturn(List.of());
        companyYpArticleService.getAllCompanyYpArticle();
        verify(companyYpArticleRepository).findAll();
        verify(companyYpArticleMapper).toDTO(anyList());
    }
}
