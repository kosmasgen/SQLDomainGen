package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CorporateStatusRepository;
import gr.knowledge.pepTest.mapper.CorporateStatusMapper;
import gr.knowledge.pepTest.serviceImpl.CorporateStatusServiceImpl;

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
class CorporateStatusServiceTest {

    @Mock
    private CorporateStatusRepository corporateStatusRepository;

    @Mock
    private CorporateStatusMapper corporateStatusMapper;

    @InjectMocks
    private CorporateStatusServiceImpl corporateStatusService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(corporateStatusRepository.findAll()).willReturn(List.of());
        corporateStatusService.getAllCorporateStatus();
        verify(corporateStatusRepository).findAll();
        verify(corporateStatusMapper).toDTO(anyList());
    }
}
