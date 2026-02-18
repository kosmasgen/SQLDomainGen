package gr.knowledge.schoolmanagement.service;

import gr.knowledge.schoolmanagement.repository.StudentRepository;
import gr.knowledge.schoolmanagement.mapper.StudentMapper;
import gr.knowledge.schoolmanagement.serviceImpl.StudentServiceImpl;

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
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(studentRepository.findAll()).willReturn(List.of());
        studentService.getAllStudent();
        verify(studentRepository).findAll();
        verify(studentMapper).toDTO(anyList());
    }
}
