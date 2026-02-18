package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.repository.CourseStudentRepository;
import gr.knowledge.schoolmanagement.mapper.CourseStudentMapper;
import gr.knowledge.schoolmanagement.serviceImpl.CourseStudentServiceImpl;

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
class CourseStudentServiceTest {

    @Mock
    private CourseStudentRepository courseStudentRepository;

    @Mock
    private CourseStudentMapper courseStudentMapper;

    @InjectMocks
    private CourseStudentServiceImpl courseStudentService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(courseStudentRepository.findAll()).willReturn(List.of());
        courseStudentService.getAllCourseStudent();
        verify(courseStudentRepository).findAll();
        verify(courseStudentMapper).toDTO(anyList());
    }
}
