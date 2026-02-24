package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.UserGeodataRepository;
import gr.knowledge.pepTest.mapper.UserGeodataMapper;
import gr.knowledge.pepTest.serviceImpl.UserGeodataServiceImpl;

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
class UserGeodataServiceTest {

    @Mock
    private UserGeodataRepository userGeodataRepository;

    @Mock
    private UserGeodataMapper userGeodataMapper;

    @InjectMocks
    private UserGeodataServiceImpl userGeodataService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(userGeodataRepository.findAll()).willReturn(List.of());
        userGeodataService.getAllUserGeodata();
        verify(userGeodataRepository).findAll();
        verify(userGeodataMapper).toDTO(anyList());
    }
}
