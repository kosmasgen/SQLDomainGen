package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.TemporaryCompanyRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyMapper;
import gr.knowledge.pepTest.serviceImpl.TemporaryCompanyServiceImpl;

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
class TemporaryCompanyServiceTest {

    @Mock
    private TemporaryCompanyRepository temporaryCompanyRepository;

    @Mock
    private TemporaryCompanyMapper temporaryCompanyMapper;

    @InjectMocks
    private TemporaryCompanyServiceImpl temporaryCompanyService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(temporaryCompanyRepository.findAll()).willReturn(List.of());
        temporaryCompanyService.getAllTemporaryCompany();
        verify(temporaryCompanyRepository).findAll();
        verify(temporaryCompanyMapper).toDTO(anyList());
    }
}
