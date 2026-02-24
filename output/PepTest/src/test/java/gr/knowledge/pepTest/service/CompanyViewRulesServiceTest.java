package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyViewRulesRepository;
import gr.knowledge.pepTest.mapper.CompanyViewRulesMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyViewRulesServiceImpl;

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
class CompanyViewRulesServiceTest {

    @Mock
    private CompanyViewRulesRepository companyViewRulesRepository;

    @Mock
    private CompanyViewRulesMapper companyViewRulesMapper;

    @InjectMocks
    private CompanyViewRulesServiceImpl companyViewRulesService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyViewRulesRepository.findAll()).willReturn(List.of());
        companyViewRulesService.getAllCompanyViewRules();
        verify(companyViewRulesRepository).findAll();
        verify(companyViewRulesMapper).toDTO(anyList());
    }
}
