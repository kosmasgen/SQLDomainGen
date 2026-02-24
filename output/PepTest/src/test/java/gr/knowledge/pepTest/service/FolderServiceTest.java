package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.FolderRepository;
import gr.knowledge.pepTest.mapper.FolderMapper;
import gr.knowledge.pepTest.serviceImpl.FolderServiceImpl;

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
class FolderServiceTest {

    @Mock
    private FolderRepository folderRepository;

    @Mock
    private FolderMapper folderMapper;

    @InjectMocks
    private FolderServiceImpl folderService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(folderRepository.findAll()).willReturn(List.of());
        folderService.getAllFolder();
        verify(folderRepository).findAll();
        verify(folderMapper).toDTO(anyList());
    }
}
