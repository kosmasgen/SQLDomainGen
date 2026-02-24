package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ExportCompanyRepository;
import gr.knowledge.pepTest.mapper.ExportCompanyMapper;
import gr.knowledge.pepTest.serviceImpl.ExportCompanyServiceImpl;

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
class ExportCompanyServiceTest {

    @Mock
    private ExportCompanyRepository exportCompanyRepository;

    @Mock
    private ExportCompanyMapper exportCompanyMapper;

    @InjectMocks
    private ExportCompanyServiceImpl exportCompanyService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(exportCompanyRepository.findAll()).willReturn(List.of());
        exportCompanyService.getAllExportCompany();
        verify(exportCompanyRepository).findAll();
        verify(exportCompanyMapper).toDTO(anyList());
    }
}
