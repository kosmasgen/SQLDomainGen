package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyProfileRepository;
import gr.knowledge.pepTest.mapper.CompanyProfileMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyProfileServiceImpl;

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
class CompanyProfileServiceTest {

    @Mock
    private CompanyProfileRepository companyProfileRepository;

    @Mock
    private CompanyProfileMapper companyProfileMapper;

    @InjectMocks
    private CompanyProfileServiceImpl companyProfileService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyProfileRepository.findAll()).willReturn(List.of());
        companyProfileService.getAllCompanyProfile();
        verify(companyProfileRepository).findAll();
        verify(companyProfileMapper).toDTO(anyList());
    }
}
