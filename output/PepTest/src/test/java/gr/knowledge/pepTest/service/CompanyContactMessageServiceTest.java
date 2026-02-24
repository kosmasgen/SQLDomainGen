package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyContactMessageRepository;
import gr.knowledge.pepTest.mapper.CompanyContactMessageMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyContactMessageServiceImpl;

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
class CompanyContactMessageServiceTest {

    @Mock
    private CompanyContactMessageRepository companyContactMessageRepository;

    @Mock
    private CompanyContactMessageMapper companyContactMessageMapper;

    @InjectMocks
    private CompanyContactMessageServiceImpl companyContactMessageService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyContactMessageRepository.findAll()).willReturn(List.of());
        companyContactMessageService.getAllCompanyContactMessage();
        verify(companyContactMessageRepository).findAll();
        verify(companyContactMessageMapper).toDTO(anyList());
    }
}
