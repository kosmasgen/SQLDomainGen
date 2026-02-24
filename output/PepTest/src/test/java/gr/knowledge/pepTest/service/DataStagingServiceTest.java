package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.DataStagingRepository;
import gr.knowledge.pepTest.mapper.DataStagingMapper;
import gr.knowledge.pepTest.serviceImpl.DataStagingServiceImpl;

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
class DataStagingServiceTest {

    @Mock
    private DataStagingRepository dataStagingRepository;

    @Mock
    private DataStagingMapper dataStagingMapper;

    @InjectMocks
    private DataStagingServiceImpl dataStagingService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(dataStagingRepository.findAll()).willReturn(List.of());
        dataStagingService.getAllDataStaging();
        verify(dataStagingRepository).findAll();
        verify(dataStagingMapper).toDTO(anyList());
    }
}
