package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.StatusHistoryRepository;
import gr.knowledge.pepTest.mapper.StatusHistoryMapper;
import gr.knowledge.pepTest.serviceImpl.StatusHistoryServiceImpl;

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
class StatusHistoryServiceTest {

    @Mock
    private StatusHistoryRepository statusHistoryRepository;

    @Mock
    private StatusHistoryMapper statusHistoryMapper;

    @InjectMocks
    private StatusHistoryServiceImpl statusHistoryService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(statusHistoryRepository.findAll()).willReturn(List.of());
        statusHistoryService.getAllStatusHistory();
        verify(statusHistoryRepository).findAll();
        verify(statusHistoryMapper).toDTO(anyList());
    }
}
