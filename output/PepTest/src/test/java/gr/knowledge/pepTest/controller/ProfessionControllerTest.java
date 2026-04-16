package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.service.ProfessionService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.dto.ProfessionSystemDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

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
@WebMvcTest(ProfessionController.class)
class ProfessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProfessionService professionService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(professionService.getAllProfessions()).willReturn(List.of(new ProfessionDto()));

        mockMvc.perform(get("/api/profession"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionService).getAllProfessions();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(professionService.getProfessionById(id)).willReturn(new ProfessionDto());

        mockMvc.perform(get("/api/profession/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionService).getProfessionById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(professionService.getProfessionById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Profession")
                        .message("Profession not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/profession/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ProfessionDto requestDto = createValidCreateProfessionDto();
        ProfessionDto responseDto = new ProfessionDto();
        given(professionService.createProfession(any(ProfessionDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionService).createProfession(any(ProfessionDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        ProfessionDto requestDto = createValidCreateProfessionDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ProfessionDto requestDto = createValidCreateProfessionDto();
        given(professionService.createProfession(any(ProfessionDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("Profession")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionDto requestDto = new ProfessionDto();
        ProfessionDto responseDto = new ProfessionDto();
        given(professionService.updateProfession(eq(id), any(ProfessionDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/profession/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionService).updateProfession(eq(id), any(ProfessionDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionDto requestDto = new ProfessionDto();
        given(professionService.updateProfession(eq(id), any(ProfessionDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Profession")
                        .message("Profession not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/profession/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(professionService).deleteProfession(id);

        mockMvc.perform(delete("/api/profession/{id}", id))
                .andExpect(status().isNoContent());

        verify(professionService).deleteProfession(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Profession")
                .message("Profession not found with id: " + id)
                .build())
                .given(professionService).deleteProfession(id);

        mockMvc.perform(delete("/api/profession/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for Profession.
     *
     * @return populated create request dto
     */
    private ProfessionDto createValidCreateProfessionDto() {
        ProfessionDto dto = new ProfessionDto();
        dto.setChamberId(1);
        dto.setParentProfession(new ProfessionDto());
        dto.setProfessionSystem(new ProfessionSystemDto());
        dto.setCode("aaaaa");
        dto.setRecdeleted(true);
        dto.setFriendlyCat(new ProfessionFriendlyCategoryDto());

        return dto;
    }

}
