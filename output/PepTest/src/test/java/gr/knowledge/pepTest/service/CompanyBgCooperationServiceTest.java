package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyBgCooperationRepository;
import gr.knowledge.pepTest.mapper.CompanyBgCooperationMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyBgCooperationServiceImpl;

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
class CompanyBgCooperationServiceTest {

    @Mock
    private CompanyBgCooperationRepository companyBgCooperationRepository;

    @Mock
    private CompanyBgCooperationMapper companyBgCooperationMapper;

    @InjectMocks
    private CompanyBgCooperationServiceImpl companyBgCooperationService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyBgCooperationRepository.findAll()).willReturn(List.of());
        companyBgCooperationService.getAllCompanyBgCooperation();
        verify(companyBgCooperationRepository).findAll();
        verify(companyBgCooperationMapper).toDTO(anyList());
    }
}
