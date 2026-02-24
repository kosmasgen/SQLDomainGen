package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CorporateStatusi18nRepository;
import gr.knowledge.pepTest.mapper.CorporateStatusi18nMapper;
import gr.knowledge.pepTest.serviceImpl.CorporateStatusi18nServiceImpl;

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
class CorporateStatusi18nServiceTest {

    @Mock
    private CorporateStatusi18nRepository corporateStatusi18nRepository;

    @Mock
    private CorporateStatusi18nMapper corporateStatusi18nMapper;

    @InjectMocks
    private CorporateStatusi18nServiceImpl corporateStatusi18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(corporateStatusi18nRepository.findAll()).willReturn(List.of());
        corporateStatusi18nService.getAllCorporateStatusi18n();
        verify(corporateStatusi18nRepository).findAll();
        verify(corporateStatusi18nMapper).toDTO(anyList());
    }
}
