package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.IncomePaymentMethodRepository;
import gr.knowledge.pepTest.mapper.IncomePaymentMethodMapper;
import gr.knowledge.pepTest.serviceImpl.IncomePaymentMethodServiceImpl;

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
class IncomePaymentMethodServiceTest {

    @Mock
    private IncomePaymentMethodRepository incomePaymentMethodRepository;

    @Mock
    private IncomePaymentMethodMapper incomePaymentMethodMapper;

    @InjectMocks
    private IncomePaymentMethodServiceImpl incomePaymentMethodService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(incomePaymentMethodRepository.findAll()).willReturn(List.of());
        incomePaymentMethodService.getAllIncomePaymentMethod();
        verify(incomePaymentMethodRepository).findAll();
        verify(incomePaymentMethodMapper).toDTO(anyList());
    }
}
