package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.BusinessLocationI18nRepository;
import gr.knowledge.pepTest.mapper.BusinessLocationI18nMapper;
import gr.knowledge.pepTest.serviceImpl.BusinessLocationI18nServiceImpl;

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
class BusinessLocationI18nServiceTest {

    @Mock
    private BusinessLocationI18nRepository businessLocationI18nRepository;

    @Mock
    private BusinessLocationI18nMapper businessLocationI18nMapper;

    @InjectMocks
    private BusinessLocationI18nServiceImpl businessLocationI18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(businessLocationI18nRepository.findAll()).willReturn(List.of());
        businessLocationI18nService.getAllBusinessLocationI18n();
        verify(businessLocationI18nRepository).findAll();
        verify(businessLocationI18nMapper).toDTO(anyList());
    }
}
