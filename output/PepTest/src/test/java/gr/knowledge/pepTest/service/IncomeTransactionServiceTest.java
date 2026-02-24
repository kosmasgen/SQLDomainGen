package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.IncomeTransactionRepository;
import gr.knowledge.pepTest.mapper.IncomeTransactionMapper;
import gr.knowledge.pepTest.serviceImpl.IncomeTransactionServiceImpl;

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
class IncomeTransactionServiceTest {

    @Mock
    private IncomeTransactionRepository incomeTransactionRepository;

    @Mock
    private IncomeTransactionMapper incomeTransactionMapper;

    @InjectMocks
    private IncomeTransactionServiceImpl incomeTransactionService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(incomeTransactionRepository.findAll()).willReturn(List.of());
        incomeTransactionService.getAllIncomeTransaction();
        verify(incomeTransactionRepository).findAll();
        verify(incomeTransactionMapper).toDTO(anyList());
    }
}
