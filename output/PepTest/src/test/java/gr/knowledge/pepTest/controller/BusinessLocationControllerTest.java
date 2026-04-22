package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.service.BusinessLocationService;
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
import java.time.LocalDateTime;
import java.util.UUID;

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
@WebMvcTest(BusinessLocationController.class)
class BusinessLocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BusinessLocationService businessLocationService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(businessLocationService.getAllBusinessLocations()).willReturn(List.of(new BusinessLocationDto()));

        mockMvc.perform(get("/api/business-location"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(businessLocationService).getAllBusinessLocations();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(businessLocationService.getBusinessLocationById(id)).willReturn(new BusinessLocationDto());

        mockMvc.perform(get("/api/business-location/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(businessLocationService).getBusinessLocationById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(businessLocationService.getBusinessLocationById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("BusinessLocation")
                        .message("BusinessLocation not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/business-location/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        BusinessLocationDto requestDto = createValidCreateBusinessLocationDto();
        BusinessLocationDto responseDto = new BusinessLocationDto();
        given(businessLocationService.createBusinessLocation(any(BusinessLocationDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/business-location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(businessLocationService).createBusinessLocation(any(BusinessLocationDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        BusinessLocationDto requestDto = createValidCreateBusinessLocationDto();
        requestDto.setCode(null);

        mockMvc.perform(post("/api/business-location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        BusinessLocationDto requestDto = createValidCreateBusinessLocationDto();
        given(businessLocationService.createBusinessLocation(any(BusinessLocationDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("BusinessLocation")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/business-location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        BusinessLocationDto requestDto = createValidCreateBusinessLocationDto();
        requestDto.setCode(null);

        mockMvc.perform(patch("/api/business-location/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        BusinessLocationDto requestDto = createValidCreateBusinessLocationDto();
        BusinessLocationDto responseDto = new BusinessLocationDto();
        given(businessLocationService.updateBusinessLocation(eq(id), any(BusinessLocationDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/business-location/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(businessLocationService).updateBusinessLocation(eq(id), any(BusinessLocationDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        BusinessLocationDto requestDto = createValidCreateBusinessLocationDto();
        given(businessLocationService.updateBusinessLocation(eq(id), any(BusinessLocationDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("BusinessLocation")
                        .message("BusinessLocation not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/business-location/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        BusinessLocationDto requestDto = createValidCreateBusinessLocationDto();
        given(businessLocationService.updateBusinessLocation(eq(id), any(BusinessLocationDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/business-location/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(businessLocationService).deleteBusinessLocation(id);

        mockMvc.perform(delete("/api/business-location/{id}", id))
                .andExpect(status().isNoContent());

        verify(businessLocationService).deleteBusinessLocation(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("BusinessLocation")
                .message("BusinessLocation not found with id: " + id)
                .build())
                .given(businessLocationService).deleteBusinessLocation(id);

        mockMvc.perform(delete("/api/business-location/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for BusinessLocation.
     *
     * @return populated create request dto
     */
    private BusinessLocationDto createValidCreateBusinessLocationDto() {
        BusinessLocationDto dto = new BusinessLocationDto();
        dto.setCode("A");
        dto.setRecdeleted(true);

        return dto;
    }

}
