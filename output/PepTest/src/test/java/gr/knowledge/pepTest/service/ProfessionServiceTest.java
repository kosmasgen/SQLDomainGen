package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ProfessionRepository;
import gr.knowledge.pepTest.mapper.ProfessionMapper;
import gr.knowledge.pepTest.serviceImpl.ProfessionServiceImpl;

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
class ProfessionServiceTest {

    @Mock
    private ProfessionRepository professionRepository;

    @Mock
    private ProfessionMapper professionMapper;

    @InjectMocks
    private ProfessionServiceImpl professionService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(professionRepository.findAll()).willReturn(List.of());
        professionService.getAllProfession();
        verify(professionRepository).findAll();
        verify(professionMapper).toDTO(anyList());
    }
}
