package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyStatusViewRulesRepository;
import gr.knowledge.pepTest.mapper.CompanyStatusViewRulesMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyStatusViewRulesServiceImpl;

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
class CompanyStatusViewRulesServiceTest {

    @Mock
    private CompanyStatusViewRulesRepository companyStatusViewRulesRepository;

    @Mock
    private CompanyStatusViewRulesMapper companyStatusViewRulesMapper;

    @InjectMocks
    private CompanyStatusViewRulesServiceImpl companyStatusViewRulesService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyStatusViewRulesRepository.findAll()).willReturn(List.of());
        companyStatusViewRulesService.getAllCompanyStatusViewRules();
        verify(companyStatusViewRulesRepository).findAll();
        verify(companyStatusViewRulesMapper).toDTO(anyList());
    }
}
