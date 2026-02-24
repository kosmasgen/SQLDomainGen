package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyRepository;
import gr.knowledge.pepTest.mapper.CompanyMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyServiceImpl;

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
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyRepository.findAll()).willReturn(List.of());
        companyService.getAllCompany();
        verify(companyRepository).findAll();
        verify(companyMapper).toDTO(anyList());
    }
}
