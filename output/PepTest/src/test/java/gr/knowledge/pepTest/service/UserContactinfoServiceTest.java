package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.UserContactinfoRepository;
import gr.knowledge.pepTest.mapper.UserContactinfoMapper;
import gr.knowledge.pepTest.serviceImpl.UserContactinfoServiceImpl;

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
class UserContactinfoServiceTest {

    @Mock
    private UserContactinfoRepository userContactinfoRepository;

    @Mock
    private UserContactinfoMapper userContactinfoMapper;

    @InjectMocks
    private UserContactinfoServiceImpl userContactinfoService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(userContactinfoRepository.findAll()).willReturn(List.of());
        userContactinfoService.getAllUserContactinfo();
        verify(userContactinfoRepository).findAll();
        verify(userContactinfoMapper).toDTO(anyList());
    }
}
