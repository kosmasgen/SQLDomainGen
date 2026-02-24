package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.ProfessionFriendlyCategoryRepository;
import gr.knowledge.pepTest.mapper.ProfessionFriendlyCategoryMapper;
import gr.knowledge.pepTest.serviceImpl.ProfessionFriendlyCategoryServiceImpl;

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
class ProfessionFriendlyCategoryServiceTest {

    @Mock
    private ProfessionFriendlyCategoryRepository professionFriendlyCategoryRepository;

    @Mock
    private ProfessionFriendlyCategoryMapper professionFriendlyCategoryMapper;

    @InjectMocks
    private ProfessionFriendlyCategoryServiceImpl professionFriendlyCategoryService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(professionFriendlyCategoryRepository.findAll()).willReturn(List.of());
        professionFriendlyCategoryService.getAllProfessionFriendlyCategory();
        verify(professionFriendlyCategoryRepository).findAll();
        verify(professionFriendlyCategoryMapper).toDTO(anyList());
    }
}
