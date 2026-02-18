package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.repository.CourseRepository;
import gr.knowledge.schoolmanagement.mapper.CourseMapper;
import gr.knowledge.schoolmanagement.serviceImpl.CourseServiceImpl;

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
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(courseRepository.findAll()).willReturn(List.of());
        courseService.getAllCourse();
        verify(courseRepository).findAll();
        verify(courseMapper).toDTO(anyList());
    }
}
