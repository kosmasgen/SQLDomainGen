package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.TemporaryCompanyi18nRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyi18nMapper;
import gr.knowledge.pepTest.serviceImpl.TemporaryCompanyi18nServiceImpl;

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
class TemporaryCompanyi18nServiceTest {

    @Mock
    private TemporaryCompanyi18nRepository temporaryCompanyi18nRepository;

    @Mock
    private TemporaryCompanyi18nMapper temporaryCompanyi18nMapper;

    @InjectMocks
    private TemporaryCompanyi18nServiceImpl temporaryCompanyi18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(temporaryCompanyi18nRepository.findAll()).willReturn(List.of());
        temporaryCompanyi18nService.getAllTemporaryCompanyi18n();
        verify(temporaryCompanyi18nRepository).findAll();
        verify(temporaryCompanyi18nMapper).toDTO(anyList());
    }
}
