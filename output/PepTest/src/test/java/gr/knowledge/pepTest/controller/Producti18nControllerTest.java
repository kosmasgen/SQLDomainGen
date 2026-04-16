package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.Producti18nDto;
import gr.knowledge.pepTest.service.Producti18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.ProductDto;

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
@WebMvcTest(Producti18nController.class)
class Producti18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private Producti18nService producti18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(producti18nService.getAllProducti18ns()).willReturn(List.of(new Producti18nDto()));

        mockMvc.perform(get("/api/producti18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(producti18nService).getAllProducti18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(producti18nService.getProducti18nById(languageId, productId)).willReturn(new Producti18nDto());

        mockMvc.perform(get("/api/producti18n/{languageId}/{productId}", languageId, productId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(producti18nService).getProducti18nById(languageId, productId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(producti18nService.getProducti18nById(languageId, productId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Producti18n")
                        .message("Producti18n not found")
                        .build());

        mockMvc.perform(get("/api/producti18n/{languageId}/{productId}", languageId, productId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        Producti18nDto requestDto = createValidCreateProducti18nDto();
        Producti18nDto responseDto = new Producti18nDto();
        given(producti18nService.createProducti18n(any(Producti18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/producti18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(producti18nService).createProducti18n(any(Producti18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        Producti18nDto requestDto = createValidCreateProducti18nDto();
        requestDto.setVersion(null);

        mockMvc.perform(post("/api/producti18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        Producti18nDto requestDto = createValidCreateProducti18nDto();
        given(producti18nService.createProducti18n(any(Producti18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("Producti18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/producti18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        Producti18nDto requestDto = new Producti18nDto();
        Producti18nDto responseDto = new Producti18nDto();
        given(producti18nService.updateProducti18n(eq(languageId), eq(productId), any(Producti18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/producti18n/{languageId}/{productId}", languageId, productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(producti18nService).updateProducti18n(eq(languageId), eq(productId), any(Producti18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        Producti18nDto requestDto = new Producti18nDto();
        given(producti18nService.updateProducti18n(eq(languageId), eq(productId), any(Producti18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Producti18n")
                        .message("Producti18n not found")
                        .build());

        mockMvc.perform(patch("/api/producti18n/{languageId}/{productId}", languageId, productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(producti18nService).deleteProducti18n(languageId, productId);

        mockMvc.perform(delete("/api/producti18n/{languageId}/{productId}", languageId, productId))
                .andExpect(status().isNoContent());

        verify(producti18nService).deleteProducti18n(languageId, productId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID productId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Producti18n")
                .message("Producti18n not found")
                .build())
                .given(producti18nService).deleteProducti18n(languageId, productId);

        mockMvc.perform(delete("/api/producti18n/{languageId}/{productId}", languageId, productId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for Producti18n.
     *
     * @return populated create request dto
     */
    private Producti18nDto createValidCreateProducti18nDto() {
        Producti18nDto dto = new Producti18nDto();
        dto.setVersion(1);
        dto.setDescription("aaaaa");
        dto.setChamberI18nId(1L);
        dto.setLanguage(new LanguagesDto());
        dto.setProduct(new ProductDto());
        dto.setRecdeleted(true);

        return dto;
    }

}
