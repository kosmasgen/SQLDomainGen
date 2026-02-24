package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.SyncrunsErrorLogRepository;
import gr.knowledge.pepTest.mapper.SyncrunsErrorLogMapper;
import gr.knowledge.pepTest.serviceImpl.SyncrunsErrorLogServiceImpl;

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
class SyncrunsErrorLogServiceTest {

    @Mock
    private SyncrunsErrorLogRepository syncrunsErrorLogRepository;

    @Mock
    private SyncrunsErrorLogMapper syncrunsErrorLogMapper;

    @InjectMocks
    private SyncrunsErrorLogServiceImpl syncrunsErrorLogService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(syncrunsErrorLogRepository.findAll()).willReturn(List.of());
        syncrunsErrorLogService.getAllSyncrunsErrorLog();
        verify(syncrunsErrorLogRepository).findAll();
        verify(syncrunsErrorLogMapper).toDTO(anyList());
    }
}
