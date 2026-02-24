package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.SyncrunsRepository;
import gr.knowledge.pepTest.mapper.SyncrunsMapper;
import gr.knowledge.pepTest.serviceImpl.SyncrunsServiceImpl;

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
class SyncrunsServiceTest {

    @Mock
    private SyncrunsRepository syncrunsRepository;

    @Mock
    private SyncrunsMapper syncrunsMapper;

    @InjectMocks
    private SyncrunsServiceImpl syncrunsService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(syncrunsRepository.findAll()).willReturn(List.of());
        syncrunsService.getAllSyncruns();
        verify(syncrunsRepository).findAll();
        verify(syncrunsMapper).toDTO(anyList());
    }
}
