package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.StatsExpenseRepository;
import gr.knowledge.pepTest.mapper.StatsExpenseMapper;
import gr.knowledge.pepTest.serviceImpl.StatsExpenseServiceImpl;

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
class StatsExpenseServiceTest {

    @Mock
    private StatsExpenseRepository statsExpenseRepository;

    @Mock
    private StatsExpenseMapper statsExpenseMapper;

    @InjectMocks
    private StatsExpenseServiceImpl statsExpenseService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(statsExpenseRepository.findAll()).willReturn(List.of());
        statsExpenseService.getAllStatsExpense();
        verify(statsExpenseRepository).findAll();
        verify(statsExpenseMapper).toDTO(anyList());
    }
}
