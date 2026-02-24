package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.BusinessLocationRepository;
import gr.knowledge.pepTest.mapper.BusinessLocationMapper;
import gr.knowledge.pepTest.serviceImpl.BusinessLocationServiceImpl;

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
class BusinessLocationServiceTest {

    @Mock
    private BusinessLocationRepository businessLocationRepository;

    @Mock
    private BusinessLocationMapper businessLocationMapper;

    @InjectMocks
    private BusinessLocationServiceImpl businessLocationService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(businessLocationRepository.findAll()).willReturn(List.of());
        businessLocationService.getAllBusinessLocation();
        verify(businessLocationRepository).findAll();
        verify(businessLocationMapper).toDTO(anyList());
    }
}
