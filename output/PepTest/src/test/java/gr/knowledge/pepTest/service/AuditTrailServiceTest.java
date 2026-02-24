package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.AuditTrailRepository;
import gr.knowledge.pepTest.mapper.AuditTrailMapper;
import gr.knowledge.pepTest.serviceImpl.AuditTrailServiceImpl;

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
class AuditTrailServiceTest {

    @Mock
    private AuditTrailRepository auditTrailRepository;

    @Mock
    private AuditTrailMapper auditTrailMapper;

    @InjectMocks
    private AuditTrailServiceImpl auditTrailService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(auditTrailRepository.findAll()).willReturn(List.of());
        auditTrailService.getAllAuditTrail();
        verify(auditTrailRepository).findAll();
        verify(auditTrailMapper).toDTO(anyList());
    }
}
