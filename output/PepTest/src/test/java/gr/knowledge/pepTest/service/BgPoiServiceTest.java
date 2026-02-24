package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.BgPoiRepository;
import gr.knowledge.pepTest.mapper.BgPoiMapper;
import gr.knowledge.pepTest.serviceImpl.BgPoiServiceImpl;

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
class BgPoiServiceTest {

    @Mock
    private BgPoiRepository bgPoiRepository;

    @Mock
    private BgPoiMapper bgPoiMapper;

    @InjectMocks
    private BgPoiServiceImpl bgPoiService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(bgPoiRepository.findAll()).willReturn(List.of());
        bgPoiService.getAllBgPoi();
        verify(bgPoiRepository).findAll();
        verify(bgPoiMapper).toDTO(anyList());
    }
}
