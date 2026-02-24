package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.Companyi18nRepository;
import gr.knowledge.pepTest.mapper.Companyi18nMapper;
import gr.knowledge.pepTest.serviceImpl.Companyi18nServiceImpl;

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
class Companyi18nServiceTest {

    @Mock
    private Companyi18nRepository companyi18nRepository;

    @Mock
    private Companyi18nMapper companyi18nMapper;

    @InjectMocks
    private Companyi18nServiceImpl companyi18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyi18nRepository.findAll()).willReturn(List.of());
        companyi18nService.getAllCompanyi18n();
        verify(companyi18nRepository).findAll();
        verify(companyi18nMapper).toDTO(anyList());
    }
}
