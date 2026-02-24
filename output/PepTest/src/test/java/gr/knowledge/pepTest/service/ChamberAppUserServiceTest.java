package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ChamberAppUserRepository;
import gr.knowledge.pepTest.mapper.ChamberAppUserMapper;
import gr.knowledge.pepTest.serviceImpl.ChamberAppUserServiceImpl;

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
class ChamberAppUserServiceTest {

    @Mock
    private ChamberAppUserRepository chamberAppUserRepository;

    @Mock
    private ChamberAppUserMapper chamberAppUserMapper;

    @InjectMocks
    private ChamberAppUserServiceImpl chamberAppUserService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(chamberAppUserRepository.findAll()).willReturn(List.of());
        chamberAppUserService.getAllChamberAppUser();
        verify(chamberAppUserRepository).findAll();
        verify(chamberAppUserMapper).toDTO(anyList());
    }
}
