package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyTitleRepository;
import gr.knowledge.pepTest.mapper.CompanyTitleMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyTitleServiceImpl;

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
class CompanyTitleServiceTest {

    @Mock
    private CompanyTitleRepository companyTitleRepository;

    @Mock
    private CompanyTitleMapper companyTitleMapper;

    @InjectMocks
    private CompanyTitleServiceImpl companyTitleService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyTitleRepository.findAll()).willReturn(List.of());
        companyTitleService.getAllCompanyTitle();
        verify(companyTitleRepository).findAll();
        verify(companyTitleMapper).toDTO(anyList());
    }
}
