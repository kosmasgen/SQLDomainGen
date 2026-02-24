package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ChAppUserContactRepository;
import gr.knowledge.pepTest.mapper.ChAppUserContactMapper;
import gr.knowledge.pepTest.serviceImpl.ChAppUserContactServiceImpl;

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
class ChAppUserContactServiceTest {

    @Mock
    private ChAppUserContactRepository chAppUserContactRepository;

    @Mock
    private ChAppUserContactMapper chAppUserContactMapper;

    @InjectMocks
    private ChAppUserContactServiceImpl chAppUserContactService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(chAppUserContactRepository.findAll()).willReturn(List.of());
        chAppUserContactService.getAllChAppUserContact();
        verify(chAppUserContactRepository).findAll();
        verify(chAppUserContactMapper).toDTO(anyList());
    }
}
