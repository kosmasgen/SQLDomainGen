package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.IncomeTypeRepository;
import gr.knowledge.pepTest.mapper.IncomeTypeMapper;
import gr.knowledge.pepTest.serviceImpl.IncomeTypeServiceImpl;

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
class IncomeTypeServiceTest {

    @Mock
    private IncomeTypeRepository incomeTypeRepository;

    @Mock
    private IncomeTypeMapper incomeTypeMapper;

    @InjectMocks
    private IncomeTypeServiceImpl incomeTypeService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(incomeTypeRepository.findAll()).willReturn(List.of());
        incomeTypeService.getAllIncomeType();
        verify(incomeTypeRepository).findAll();
        verify(incomeTypeMapper).toDTO(anyList());
    }
}
