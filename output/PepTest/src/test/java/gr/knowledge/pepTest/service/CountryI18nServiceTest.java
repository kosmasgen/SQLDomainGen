package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CountryI18nRepository;
import gr.knowledge.pepTest.mapper.CountryI18nMapper;
import gr.knowledge.pepTest.serviceImpl.CountryI18nServiceImpl;

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
class CountryI18nServiceTest {

    @Mock
    private CountryI18nRepository countryI18nRepository;

    @Mock
    private CountryI18nMapper countryI18nMapper;

    @InjectMocks
    private CountryI18nServiceImpl countryI18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(countryI18nRepository.findAll()).willReturn(List.of());
        countryI18nService.getAllCountryI18n();
        verify(countryI18nRepository).findAll();
        verify(countryI18nMapper).toDTO(anyList());
    }
}
