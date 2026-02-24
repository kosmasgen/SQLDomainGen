package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.IncomeGemiPaymentRepository;
import gr.knowledge.pepTest.mapper.IncomeGemiPaymentMapper;
import gr.knowledge.pepTest.serviceImpl.IncomeGemiPaymentServiceImpl;

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
class IncomeGemiPaymentServiceTest {

    @Mock
    private IncomeGemiPaymentRepository incomeGemiPaymentRepository;

    @Mock
    private IncomeGemiPaymentMapper incomeGemiPaymentMapper;

    @InjectMocks
    private IncomeGemiPaymentServiceImpl incomeGemiPaymentService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(incomeGemiPaymentRepository.findAll()).willReturn(List.of());
        incomeGemiPaymentService.getAllIncomeGemiPayment();
        verify(incomeGemiPaymentRepository).findAll();
        verify(incomeGemiPaymentMapper).toDTO(anyList());
    }
}
