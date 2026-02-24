package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyYpFileRepository;
import gr.knowledge.pepTest.mapper.CompanyYpFileMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyYpFileServiceImpl;

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
class CompanyYpFileServiceTest {

    @Mock
    private CompanyYpFileRepository companyYpFileRepository;

    @Mock
    private CompanyYpFileMapper companyYpFileMapper;

    @InjectMocks
    private CompanyYpFileServiceImpl companyYpFileService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyYpFileRepository.findAll()).willReturn(List.of());
        companyYpFileService.getAllCompanyYpFile();
        verify(companyYpFileRepository).findAll();
        verify(companyYpFileMapper).toDTO(anyList());
    }
}
