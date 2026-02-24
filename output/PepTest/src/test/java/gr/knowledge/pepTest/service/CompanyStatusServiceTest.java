package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyStatusRepository;
import gr.knowledge.pepTest.mapper.CompanyStatusMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyStatusServiceImpl;

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
class CompanyStatusServiceTest {

    @Mock
    private CompanyStatusRepository companyStatusRepository;

    @Mock
    private CompanyStatusMapper companyStatusMapper;

    @InjectMocks
    private CompanyStatusServiceImpl companyStatusService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyStatusRepository.findAll()).willReturn(List.of());
        companyStatusService.getAllCompanyStatus();
        verify(companyStatusRepository).findAll();
        verify(companyStatusMapper).toDTO(anyList());
    }
}
