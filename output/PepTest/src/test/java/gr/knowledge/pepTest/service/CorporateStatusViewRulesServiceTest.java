package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CorporateStatusViewRulesRepository;
import gr.knowledge.pepTest.mapper.CorporateStatusViewRulesMapper;
import gr.knowledge.pepTest.serviceImpl.CorporateStatusViewRulesServiceImpl;

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
class CorporateStatusViewRulesServiceTest {

    @Mock
    private CorporateStatusViewRulesRepository corporateStatusViewRulesRepository;

    @Mock
    private CorporateStatusViewRulesMapper corporateStatusViewRulesMapper;

    @InjectMocks
    private CorporateStatusViewRulesServiceImpl corporateStatusViewRulesService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(corporateStatusViewRulesRepository.findAll()).willReturn(List.of());
        corporateStatusViewRulesService.getAllCorporateStatusViewRules();
        verify(corporateStatusViewRulesRepository).findAll();
        verify(corporateStatusViewRulesMapper).toDTO(anyList());
    }
}
