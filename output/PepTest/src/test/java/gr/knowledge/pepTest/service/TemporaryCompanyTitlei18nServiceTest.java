package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.TemporaryCompanyTitlei18nRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyTitlei18nMapper;
import gr.knowledge.pepTest.serviceImpl.TemporaryCompanyTitlei18nServiceImpl;

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
class TemporaryCompanyTitlei18nServiceTest {

    @Mock
    private TemporaryCompanyTitlei18nRepository temporaryCompanyTitlei18nRepository;

    @Mock
    private TemporaryCompanyTitlei18nMapper temporaryCompanyTitlei18nMapper;

    @InjectMocks
    private TemporaryCompanyTitlei18nServiceImpl temporaryCompanyTitlei18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(temporaryCompanyTitlei18nRepository.findAll()).willReturn(List.of());
        temporaryCompanyTitlei18nService.getAllTemporaryCompanyTitlei18n();
        verify(temporaryCompanyTitlei18nRepository).findAll();
        verify(temporaryCompanyTitlei18nMapper).toDTO(anyList());
    }
}
