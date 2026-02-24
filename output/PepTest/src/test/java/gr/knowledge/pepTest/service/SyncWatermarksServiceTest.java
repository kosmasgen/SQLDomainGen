package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.SyncWatermarksRepository;
import gr.knowledge.pepTest.mapper.SyncWatermarksMapper;
import gr.knowledge.pepTest.serviceImpl.SyncWatermarksServiceImpl;

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
class SyncWatermarksServiceTest {

    @Mock
    private SyncWatermarksRepository syncWatermarksRepository;

    @Mock
    private SyncWatermarksMapper syncWatermarksMapper;

    @InjectMocks
    private SyncWatermarksServiceImpl syncWatermarksService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(syncWatermarksRepository.findAll()).willReturn(List.of());
        syncWatermarksService.getAllSyncWatermarks();
        verify(syncWatermarksRepository).findAll();
        verify(syncWatermarksMapper).toDTO(anyList());
    }
}
