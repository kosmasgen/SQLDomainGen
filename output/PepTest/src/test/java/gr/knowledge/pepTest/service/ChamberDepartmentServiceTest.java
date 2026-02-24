package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ChamberDepartmentRepository;
import gr.knowledge.pepTest.mapper.ChamberDepartmentMapper;
import gr.knowledge.pepTest.serviceImpl.ChamberDepartmentServiceImpl;

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
class ChamberDepartmentServiceTest {

    @Mock
    private ChamberDepartmentRepository chamberDepartmentRepository;

    @Mock
    private ChamberDepartmentMapper chamberDepartmentMapper;

    @InjectMocks
    private ChamberDepartmentServiceImpl chamberDepartmentService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(chamberDepartmentRepository.findAll()).willReturn(List.of());
        chamberDepartmentService.getAllChamberDepartment();
        verify(chamberDepartmentRepository).findAll();
        verify(chamberDepartmentMapper).toDTO(anyList());
    }
}
