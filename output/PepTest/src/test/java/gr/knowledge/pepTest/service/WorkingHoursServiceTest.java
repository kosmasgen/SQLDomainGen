package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.WorkingHoursRepository;
import gr.knowledge.pepTest.mapper.WorkingHoursMapper;
import gr.knowledge.pepTest.serviceImpl.WorkingHoursServiceImpl;

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
class WorkingHoursServiceTest {

    @Mock
    private WorkingHoursRepository workingHoursRepository;

    @Mock
    private WorkingHoursMapper workingHoursMapper;

    @InjectMocks
    private WorkingHoursServiceImpl workingHoursService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(workingHoursRepository.findAll()).willReturn(List.of());
        workingHoursService.getAllWorkingHours();
        verify(workingHoursRepository).findAll();
        verify(workingHoursMapper).toDTO(anyList());
    }
}
