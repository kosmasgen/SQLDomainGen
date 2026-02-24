package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ProfessionKindRepository;
import gr.knowledge.pepTest.mapper.ProfessionKindMapper;
import gr.knowledge.pepTest.serviceImpl.ProfessionKindServiceImpl;

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
class ProfessionKindServiceTest {

    @Mock
    private ProfessionKindRepository professionKindRepository;

    @Mock
    private ProfessionKindMapper professionKindMapper;

    @InjectMocks
    private ProfessionKindServiceImpl professionKindService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(professionKindRepository.findAll()).willReturn(List.of());
        professionKindService.getAllProfessionKind();
        verify(professionKindRepository).findAll();
        verify(professionKindMapper).toDTO(anyList());
    }
}
