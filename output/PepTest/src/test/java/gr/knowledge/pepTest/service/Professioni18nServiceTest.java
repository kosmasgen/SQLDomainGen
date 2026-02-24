package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.Professioni18nRepository;
import gr.knowledge.pepTest.mapper.Professioni18nMapper;
import gr.knowledge.pepTest.serviceImpl.Professioni18nServiceImpl;

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
class Professioni18nServiceTest {

    @Mock
    private Professioni18nRepository professioni18nRepository;

    @Mock
    private Professioni18nMapper professioni18nMapper;

    @InjectMocks
    private Professioni18nServiceImpl professioni18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(professioni18nRepository.findAll()).willReturn(List.of());
        professioni18nService.getAllProfessioni18n();
        verify(professioni18nRepository).findAll();
        verify(professioni18nMapper).toDTO(anyList());
    }
}
