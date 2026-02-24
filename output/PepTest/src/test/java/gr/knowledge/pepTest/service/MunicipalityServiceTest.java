package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.MunicipalityRepository;
import gr.knowledge.pepTest.mapper.MunicipalityMapper;
import gr.knowledge.pepTest.serviceImpl.MunicipalityServiceImpl;

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
class MunicipalityServiceTest {

    @Mock
    private MunicipalityRepository municipalityRepository;

    @Mock
    private MunicipalityMapper municipalityMapper;

    @InjectMocks
    private MunicipalityServiceImpl municipalityService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(municipalityRepository.findAll()).willReturn(List.of());
        municipalityService.getAllMunicipality();
        verify(municipalityRepository).findAll();
        verify(municipalityMapper).toDTO(anyList());
    }
}
