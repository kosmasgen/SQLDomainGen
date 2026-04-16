package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.service.ChamberDepartmentService;
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
@WebMvcTest(ChamberDepartmentController.class)
class ChamberDepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ChamberDepartmentService chamberDepartmentService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(chamberDepartmentService.getAllChamberDepartments()).willReturn(List.of(new ChamberDepartmentDto()));

        mockMvc.perform(get("/api/chamber-department"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberDepartmentService).getAllChamberDepartments();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(chamberDepartmentService.getChamberDepartmentById(id)).willReturn(new ChamberDepartmentDto());

        mockMvc.perform(get("/api/chamber-department/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberDepartmentService).getChamberDepartmentById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(chamberDepartmentService.getChamberDepartmentById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChamberDepartment")
                        .message("ChamberDepartment not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/chamber-department/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ChamberDepartmentDto requestDto = createValidCreateChamberDepartmentDto();
        ChamberDepartmentDto responseDto = new ChamberDepartmentDto();
        given(chamberDepartmentService.createChamberDepartment(any(ChamberDepartmentDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/chamber-department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberDepartmentService).createChamberDepartment(any(ChamberDepartmentDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        ChamberDepartmentDto requestDto = createValidCreateChamberDepartmentDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(post("/api/chamber-department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ChamberDepartmentDto requestDto = createValidCreateChamberDepartmentDto();
        given(chamberDepartmentService.createChamberDepartment(any(ChamberDepartmentDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ChamberDepartment")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/chamber-department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ChamberDepartmentDto requestDto = new ChamberDepartmentDto();
        ChamberDepartmentDto responseDto = new ChamberDepartmentDto();
        given(chamberDepartmentService.updateChamberDepartment(eq(id), any(ChamberDepartmentDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/chamber-department/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberDepartmentService).updateChamberDepartment(eq(id), any(ChamberDepartmentDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ChamberDepartmentDto requestDto = new ChamberDepartmentDto();
        given(chamberDepartmentService.updateChamberDepartment(eq(id), any(ChamberDepartmentDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChamberDepartment")
                        .message("ChamberDepartment not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/chamber-department/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(chamberDepartmentService).deleteChamberDepartment(id);

        mockMvc.perform(delete("/api/chamber-department/{id}", id))
                .andExpect(status().isNoContent());

        verify(chamberDepartmentService).deleteChamberDepartment(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChamberDepartment")
                .message("ChamberDepartment not found with id: " + id)
                .build())
                .given(chamberDepartmentService).deleteChamberDepartment(id);

        mockMvc.perform(delete("/api/chamber-department/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ChamberDepartment.
     *
     * @return populated create request dto
     */
    private ChamberDepartmentDto createValidCreateChamberDepartmentDto() {
        ChamberDepartmentDto dto = new ChamberDepartmentDto();
        dto.setRecdeleted(true);
        dto.setCd("aaaaa");

        return dto;
    }

}
