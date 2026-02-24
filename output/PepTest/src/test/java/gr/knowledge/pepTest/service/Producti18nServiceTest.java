package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.Producti18nRepository;
import gr.knowledge.pepTest.mapper.Producti18nMapper;
import gr.knowledge.pepTest.serviceImpl.Producti18nServiceImpl;

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
class Producti18nServiceTest {

    @Mock
    private Producti18nRepository producti18nRepository;

    @Mock
    private Producti18nMapper producti18nMapper;

    @InjectMocks
    private Producti18nServiceImpl producti18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(producti18nRepository.findAll()).willReturn(List.of());
        producti18nService.getAllProducti18n();
        verify(producti18nRepository).findAll();
        verify(producti18nMapper).toDTO(anyList());
    }
}
