package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyFileRepository;
import gr.knowledge.pepTest.mapper.CompanyFileMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyFileServiceImpl;

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
class CompanyFileServiceTest {

    @Mock
    private CompanyFileRepository companyFileRepository;

    @Mock
    private CompanyFileMapper companyFileMapper;

    @InjectMocks
    private CompanyFileServiceImpl companyFileService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyFileRepository.findAll()).willReturn(List.of());
        companyFileService.getAllCompanyFile();
        verify(companyFileRepository).findAll();
        verify(companyFileMapper).toDTO(anyList());
    }
}
