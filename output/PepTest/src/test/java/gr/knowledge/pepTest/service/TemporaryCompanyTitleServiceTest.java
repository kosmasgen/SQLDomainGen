package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.TemporaryCompanyTitleRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyTitleMapper;
import gr.knowledge.pepTest.serviceImpl.TemporaryCompanyTitleServiceImpl;

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
class TemporaryCompanyTitleServiceTest {

    @Mock
    private TemporaryCompanyTitleRepository temporaryCompanyTitleRepository;

    @Mock
    private TemporaryCompanyTitleMapper temporaryCompanyTitleMapper;

    @InjectMocks
    private TemporaryCompanyTitleServiceImpl temporaryCompanyTitleService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(temporaryCompanyTitleRepository.findAll()).willReturn(List.of());
        temporaryCompanyTitleService.getAllTemporaryCompanyTitle();
        verify(temporaryCompanyTitleRepository).findAll();
        verify(temporaryCompanyTitleMapper).toDTO(anyList());
    }
}
