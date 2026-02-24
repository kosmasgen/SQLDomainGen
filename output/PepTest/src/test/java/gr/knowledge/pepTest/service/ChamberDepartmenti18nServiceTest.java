package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ChamberDepartmenti18nRepository;
import gr.knowledge.pepTest.mapper.ChamberDepartmenti18nMapper;
import gr.knowledge.pepTest.serviceImpl.ChamberDepartmenti18nServiceImpl;

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
class ChamberDepartmenti18nServiceTest {

    @Mock
    private ChamberDepartmenti18nRepository chamberDepartmenti18nRepository;

    @Mock
    private ChamberDepartmenti18nMapper chamberDepartmenti18nMapper;

    @InjectMocks
    private ChamberDepartmenti18nServiceImpl chamberDepartmenti18nService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(chamberDepartmenti18nRepository.findAll()).willReturn(List.of());
        chamberDepartmenti18nService.getAllChamberDepartmenti18n();
        verify(chamberDepartmenti18nRepository).findAll();
        verify(chamberDepartmenti18nMapper).toDTO(anyList());
    }
}
