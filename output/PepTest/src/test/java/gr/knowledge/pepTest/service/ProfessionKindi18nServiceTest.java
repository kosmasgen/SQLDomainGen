package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ProfessionKindi18nRepository;
import gr.knowledge.pepTest.mapper.ProfessionKindi18nMapper;
import gr.knowledge.pepTest.serviceImpl.ProfessionKindi18nServiceImpl;

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
class ProfessionKindi18nServiceTest {

    @Mock
    private ProfessionKindi18nRepository professionKindi18nRepository;

    @Mock
    private ProfessionKindi18nMapper professionKindi18nMapper;

    @InjectMocks
    private ProfessionKindi18nServiceImpl professionKindi18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(professionKindi18nRepository.findAll()).willReturn(List.of());
        professionKindi18nService.getAllProfessionKindi18n();
        verify(professionKindi18nRepository).findAll();
        verify(professionKindi18nMapper).toDTO(anyList());
    }
}
