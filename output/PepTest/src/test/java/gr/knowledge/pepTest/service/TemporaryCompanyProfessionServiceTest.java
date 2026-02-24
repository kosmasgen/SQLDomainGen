package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.TemporaryCompanyProfessionRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyProfessionMapper;
import gr.knowledge.pepTest.serviceImpl.TemporaryCompanyProfessionServiceImpl;

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
class TemporaryCompanyProfessionServiceTest {

    @Mock
    private TemporaryCompanyProfessionRepository temporaryCompanyProfessionRepository;

    @Mock
    private TemporaryCompanyProfessionMapper temporaryCompanyProfessionMapper;

    @InjectMocks
    private TemporaryCompanyProfessionServiceImpl temporaryCompanyProfessionService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(temporaryCompanyProfessionRepository.findAll()).willReturn(List.of());
        temporaryCompanyProfessionService.getAllTemporaryCompanyProfession();
        verify(temporaryCompanyProfessionRepository).findAll();
        verify(temporaryCompanyProfessionMapper).toDTO(anyList());
    }
}
