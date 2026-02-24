package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyArticleFileRepository;
import gr.knowledge.pepTest.mapper.CompanyArticleFileMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyArticleFileServiceImpl;

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
class CompanyArticleFileServiceTest {

    @Mock
    private CompanyArticleFileRepository companyArticleFileRepository;

    @Mock
    private CompanyArticleFileMapper companyArticleFileMapper;

    @InjectMocks
    private CompanyArticleFileServiceImpl companyArticleFileService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyArticleFileRepository.findAll()).willReturn(List.of());
        companyArticleFileService.getAllCompanyArticleFile();
        verify(companyArticleFileRepository).findAll();
        verify(companyArticleFileMapper).toDTO(anyList());
    }
}
