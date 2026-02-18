package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.repository.SchoolRepository;
import gr.knowledge.schoolmanagement.mapper.SchoolMapper;
import gr.knowledge.schoolmanagement.serviceImpl.SchoolServiceImpl;

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
class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private SchoolMapper schoolMapper;

    @InjectMocks
    private SchoolServiceImpl schoolService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(schoolRepository.findAll()).willReturn(List.of());
        schoolService.getAllSchool();
        verify(schoolRepository).findAll();
        verify(schoolMapper).toDTO(anyList());
    }
}
