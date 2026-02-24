package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyProfessionRepository;
import gr.knowledge.pepTest.mapper.CompanyProfessionMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyProfessionServiceImpl;

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
class CompanyProfessionServiceTest {

    @Mock
    private CompanyProfessionRepository companyProfessionRepository;

    @Mock
    private CompanyProfessionMapper companyProfessionMapper;

    @InjectMocks
    private CompanyProfessionServiceImpl companyProfessionService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyProfessionRepository.findAll()).willReturn(List.of());
        companyProfessionService.getAllCompanyProfession();
        verify(companyProfessionRepository).findAll();
        verify(companyProfessionMapper).toDTO(anyList());
    }
}
