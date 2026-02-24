package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.MunicipalityI18nRepository;
import gr.knowledge.pepTest.mapper.MunicipalityI18nMapper;
import gr.knowledge.pepTest.serviceImpl.MunicipalityI18nServiceImpl;

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
class MunicipalityI18nServiceTest {

    @Mock
    private MunicipalityI18nRepository municipalityI18nRepository;

    @Mock
    private MunicipalityI18nMapper municipalityI18nMapper;

    @InjectMocks
    private MunicipalityI18nServiceImpl municipalityI18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(municipalityI18nRepository.findAll()).willReturn(List.of());
        municipalityI18nService.getAllMunicipalityI18n();
        verify(municipalityI18nRepository).findAll();
        verify(municipalityI18nMapper).toDTO(anyList());
    }
}
