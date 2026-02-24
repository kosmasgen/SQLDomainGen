package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ProfessionSystemRepository;
import gr.knowledge.pepTest.mapper.ProfessionSystemMapper;
import gr.knowledge.pepTest.serviceImpl.ProfessionSystemServiceImpl;

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
class ProfessionSystemServiceTest {

    @Mock
    private ProfessionSystemRepository professionSystemRepository;

    @Mock
    private ProfessionSystemMapper professionSystemMapper;

    @InjectMocks
    private ProfessionSystemServiceImpl professionSystemService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(professionSystemRepository.findAll()).willReturn(List.of());
        professionSystemService.getAllProfessionSystem();
        verify(professionSystemRepository).findAll();
        verify(professionSystemMapper).toDTO(anyList());
    }
}
