package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ExportCompProdCountryRepository;
import gr.knowledge.pepTest.mapper.ExportCompProdCountryMapper;
import gr.knowledge.pepTest.serviceImpl.ExportCompProdCountryServiceImpl;

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
class ExportCompProdCountryServiceTest {

    @Mock
    private ExportCompProdCountryRepository exportCompProdCountryRepository;

    @Mock
    private ExportCompProdCountryMapper exportCompProdCountryMapper;

    @InjectMocks
    private ExportCompProdCountryServiceImpl exportCompProdCountryService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(exportCompProdCountryRepository.findAll()).willReturn(List.of());
        exportCompProdCountryService.getAllExportCompProdCountry();
        verify(exportCompProdCountryRepository).findAll();
        verify(exportCompProdCountryMapper).toDTO(anyList());
    }
}
