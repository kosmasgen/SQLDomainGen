package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.BgPoiI18nRepository;
import gr.knowledge.pepTest.mapper.BgPoiI18nMapper;
import gr.knowledge.pepTest.serviceImpl.BgPoiI18nServiceImpl;

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
class BgPoiI18nServiceTest {

    @Mock
    private BgPoiI18nRepository bgPoiI18nRepository;

    @Mock
    private BgPoiI18nMapper bgPoiI18nMapper;

    @InjectMocks
    private BgPoiI18nServiceImpl bgPoiI18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(bgPoiI18nRepository.findAll()).willReturn(List.of());
        bgPoiI18nService.getAllBgPoiI18n();
        verify(bgPoiI18nRepository).findAll();
        verify(bgPoiI18nMapper).toDTO(anyList());
    }
}
