package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.LanguagesRepository;
import gr.knowledge.pepTest.mapper.LanguagesMapper;
import gr.knowledge.pepTest.serviceImpl.LanguagesServiceImpl;

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
class LanguagesServiceTest {

    @Mock
    private LanguagesRepository languagesRepository;

    @Mock
    private LanguagesMapper languagesMapper;

    @InjectMocks
    private LanguagesServiceImpl languagesService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(languagesRepository.findAll()).willReturn(List.of());
        languagesService.getAllLanguages();
        verify(languagesRepository).findAll();
        verify(languagesMapper).toDTO(anyList());
    }
}
