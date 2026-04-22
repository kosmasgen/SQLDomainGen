package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.service.ProfessionFriendlyCategoryService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProfessionFriendlyCategoryController.class)
class ProfessionFriendlyCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProfessionFriendlyCategoryService professionFriendlyCategoryService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(professionFriendlyCategoryService.getAllProfessionFriendlyCategories()).willReturn(List.of(new ProfessionFriendlyCategoryDto()));

        mockMvc.perform(get("/api/profession-friendly-category"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionFriendlyCategoryService).getAllProfessionFriendlyCategories();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        String id = "A";
        given(professionFriendlyCategoryService.getProfessionFriendlyCategoryById(id)).willReturn(new ProfessionFriendlyCategoryDto());

        mockMvc.perform(get("/api/profession-friendly-category/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionFriendlyCategoryService).getProfessionFriendlyCategoryById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        String id = "A";
        given(professionFriendlyCategoryService.getProfessionFriendlyCategoryById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ProfessionFriendlyCategory")
                        .message("ProfessionFriendlyCategory not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/profession-friendly-category/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ProfessionFriendlyCategoryDto requestDto = createValidCreateProfessionFriendlyCategoryDto();
        ProfessionFriendlyCategoryDto responseDto = new ProfessionFriendlyCategoryDto();
        given(professionFriendlyCategoryService.createProfessionFriendlyCategory(any(ProfessionFriendlyCategoryDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/profession-friendly-category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionFriendlyCategoryService).createProfessionFriendlyCategory(any(ProfessionFriendlyCategoryDto.class));
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ProfessionFriendlyCategoryDto requestDto = createValidCreateProfessionFriendlyCategoryDto();
        given(professionFriendlyCategoryService.createProfessionFriendlyCategory(any(ProfessionFriendlyCategoryDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ProfessionFriendlyCategory")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/profession-friendly-category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        String id = "A";
        ProfessionFriendlyCategoryDto requestDto = createValidCreateProfessionFriendlyCategoryDto();
        ProfessionFriendlyCategoryDto responseDto = new ProfessionFriendlyCategoryDto();
        given(professionFriendlyCategoryService.updateProfessionFriendlyCategory(eq(id), any(ProfessionFriendlyCategoryDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/profession-friendly-category/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionFriendlyCategoryService).updateProfessionFriendlyCategory(eq(id), any(ProfessionFriendlyCategoryDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        String id = "A";
        ProfessionFriendlyCategoryDto requestDto = createValidCreateProfessionFriendlyCategoryDto();
        given(professionFriendlyCategoryService.updateProfessionFriendlyCategory(eq(id), any(ProfessionFriendlyCategoryDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ProfessionFriendlyCategory")
                        .message("ProfessionFriendlyCategory not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/profession-friendly-category/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        String id = "A";
        ProfessionFriendlyCategoryDto requestDto = createValidCreateProfessionFriendlyCategoryDto();
        given(professionFriendlyCategoryService.updateProfessionFriendlyCategory(eq(id), any(ProfessionFriendlyCategoryDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/profession-friendly-category/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        String id = "A";
        willDoNothing().given(professionFriendlyCategoryService).deleteProfessionFriendlyCategory(id);

        mockMvc.perform(delete("/api/profession-friendly-category/{id}", id))
                .andExpect(status().isNoContent());

        verify(professionFriendlyCategoryService).deleteProfessionFriendlyCategory(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        String id = "A";
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ProfessionFriendlyCategory")
                .message("ProfessionFriendlyCategory not found with id: " + id)
                .build())
                .given(professionFriendlyCategoryService).deleteProfessionFriendlyCategory(id);

        mockMvc.perform(delete("/api/profession-friendly-category/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ProfessionFriendlyCategory.
     *
     * @return populated create request dto
     */
    private ProfessionFriendlyCategoryDto createValidCreateProfessionFriendlyCategoryDto() {
        return new ProfessionFriendlyCategoryDto();
    }

}
