package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.repository.CompanyFavouritesRepository;
import gr.knowledge.pepTest.mapper.CompanyFavouritesMapper;
import gr.knowledge.pepTest.serviceImpl.CompanyFavouritesServiceImpl;

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
class CompanyFavouritesServiceTest {

    @Mock
    private CompanyFavouritesRepository companyFavouritesRepository;

    @Mock
    private CompanyFavouritesMapper companyFavouritesMapper;

    @InjectMocks
    private CompanyFavouritesServiceImpl companyFavouritesService;

    @Test
    void getAllShouldDelegateToRepositoryAndMapper() {
        given(companyFavouritesRepository.findAll()).willReturn(List.of());
        companyFavouritesService.getAllCompanyFavourites();
        verify(companyFavouritesRepository).findAll();
        verify(companyFavouritesMapper).toDTO(anyList());
    }
}
