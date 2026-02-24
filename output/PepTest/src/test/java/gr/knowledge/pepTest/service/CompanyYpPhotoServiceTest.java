package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyYpPhotoRepository;
import gr.knowledge.pepTest.mapper.CompanyYpPhotoMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyYpPhotoServiceImpl;

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
class CompanyYpPhotoServiceTest {

    @Mock
    private CompanyYpPhotoRepository companyYpPhotoRepository;

    @Mock
    private CompanyYpPhotoMapper companyYpPhotoMapper;

    @InjectMocks
    private CompanyYpPhotoServiceImpl companyYpPhotoService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyYpPhotoRepository.findAll()).willReturn(List.of());
        companyYpPhotoService.getAllCompanyYpPhoto();
        verify(companyYpPhotoRepository).findAll();
        verify(companyYpPhotoMapper).toDTO(anyList());
    }
}
