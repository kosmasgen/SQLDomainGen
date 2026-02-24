package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ChAppUserContactI18nRepository;
import gr.knowledge.pepTest.mapper.ChAppUserContactI18nMapper;
import gr.knowledge.pepTest.serviceImpl.ChAppUserContactI18nServiceImpl;

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
class ChAppUserContactI18nServiceTest {

    @Mock
    private ChAppUserContactI18nRepository chAppUserContactI18nRepository;

    @Mock
    private ChAppUserContactI18nMapper chAppUserContactI18nMapper;

    @InjectMocks
    private ChAppUserContactI18nServiceImpl chAppUserContactI18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(chAppUserContactI18nRepository.findAll()).willReturn(List.of());
        chAppUserContactI18nService.getAllChAppUserContactI18n();
        verify(chAppUserContactI18nRepository).findAll();
        verify(chAppUserContactI18nMapper).toDTO(anyList());
    }
}
