package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CountryRepository;
import gr.knowledge.pepTest.mapper.CountryMapper;
import gr.knowledge.pepTest.serviceImpl.CountryServiceImpl;

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
class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(countryRepository.findAll()).willReturn(List.of());
        countryService.getAllCountry();
        verify(countryRepository).findAll();
        verify(countryMapper).toDTO(anyList());
    }
}
