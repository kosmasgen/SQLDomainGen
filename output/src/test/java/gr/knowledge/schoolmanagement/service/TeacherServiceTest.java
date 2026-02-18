package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.repository.TeacherRepository;
import gr.knowledge.schoolmanagement.mapper.TeacherMapper;
import gr.knowledge.schoolmanagement.serviceImpl.TeacherServiceImpl;

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
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private TeacherMapper teacherMapper;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(teacherRepository.findAll()).willReturn(List.of());
        teacherService.getAllTeacher();
        verify(teacherRepository).findAll();
        verify(teacherMapper).toDTO(anyList());
    }
}
