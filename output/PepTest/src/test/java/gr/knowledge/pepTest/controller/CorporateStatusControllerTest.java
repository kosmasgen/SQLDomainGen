package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.service.CorporateStatusService;
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
@WebMvcTest(CorporateStatusController.class)
class CorporateStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CorporateStatusService corporateStatusService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(corporateStatusService.getAllCorporateStatuses()).willReturn(List.of(new CorporateStatusDto()));

        mockMvc.perform(get("/api/corporate-status"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusService).getAllCorporateStatuses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(corporateStatusService.getCorporateStatusById(id)).willReturn(new CorporateStatusDto());

        mockMvc.perform(get("/api/corporate-status/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusService).getCorporateStatusById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(corporateStatusService.getCorporateStatusById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CorporateStatus")
                        .message("CorporateStatus not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/corporate-status/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CorporateStatusDto requestDto = createValidCreateCorporateStatusDto();
        CorporateStatusDto responseDto = new CorporateStatusDto();
        given(corporateStatusService.createCorporateStatus(any(CorporateStatusDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/corporate-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusService).createCorporateStatus(any(CorporateStatusDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CorporateStatusDto requestDto = createValidCreateCorporateStatusDto();
        requestDto.setCd(null);

        mockMvc.perform(post("/api/corporate-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CorporateStatusDto requestDto = createValidCreateCorporateStatusDto();
        given(corporateStatusService.createCorporateStatus(any(CorporateStatusDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CorporateStatus")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/corporate-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CorporateStatusDto requestDto = new CorporateStatusDto();
        CorporateStatusDto responseDto = new CorporateStatusDto();
        given(corporateStatusService.updateCorporateStatus(eq(id), any(CorporateStatusDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/corporate-status/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusService).updateCorporateStatus(eq(id), any(CorporateStatusDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CorporateStatusDto requestDto = new CorporateStatusDto();
        given(corporateStatusService.updateCorporateStatus(eq(id), any(CorporateStatusDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CorporateStatus")
                        .message("CorporateStatus not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/corporate-status/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(corporateStatusService).deleteCorporateStatus(id);

        mockMvc.perform(delete("/api/corporate-status/{id}", id))
                .andExpect(status().isNoContent());

        verify(corporateStatusService).deleteCorporateStatus(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CorporateStatus")
                .message("CorporateStatus not found with id: " + id)
                .build())
                .given(corporateStatusService).deleteCorporateStatus(id);

        mockMvc.perform(delete("/api/corporate-status/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CorporateStatus.
     *
     * @return populated create request dto
     */
    private CorporateStatusDto createValidCreateCorporateStatusDto() {
        CorporateStatusDto dto = new CorporateStatusDto();
        dto.setCd("aaaaa");
        dto.setRecdeleted(true);

        return dto;
    }

}
