package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;
import gr.knowledge.pepTest.service.ChamberDepartmenti18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.dto.LanguagesDto;

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
@WebMvcTest(ChamberDepartmenti18nController.class)
class ChamberDepartmenti18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ChamberDepartmenti18nService chamberDepartmenti18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(chamberDepartmenti18nService.getAllChamberDepartmenti18ns()).willReturn(List.of(new ChamberDepartmenti18nDto()));

        mockMvc.perform(get("/api/chamber-departmenti18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberDepartmenti18nService).getAllChamberDepartmenti18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(chamberDepartmenti18nService.getChamberDepartmenti18nById(departmentId, languageId)).willReturn(new ChamberDepartmenti18nDto());

        mockMvc.perform(get("/api/chamber-departmenti18n/{departmentId}/{languageId}", departmentId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberDepartmenti18nService).getChamberDepartmenti18nById(departmentId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(chamberDepartmenti18nService.getChamberDepartmenti18nById(departmentId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChamberDepartmenti18n")
                        .message("ChamberDepartmenti18n not found")
                        .build());

        mockMvc.perform(get("/api/chamber-departmenti18n/{departmentId}/{languageId}", departmentId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ChamberDepartmenti18nDto requestDto = createValidCreateChamberDepartmenti18nDto();
        ChamberDepartmenti18nDto responseDto = new ChamberDepartmenti18nDto();
        given(chamberDepartmenti18nService.createChamberDepartmenti18n(any(ChamberDepartmenti18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/chamber-departmenti18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberDepartmenti18nService).createChamberDepartmenti18n(any(ChamberDepartmenti18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        ChamberDepartmenti18nDto requestDto = createValidCreateChamberDepartmenti18nDto();
        requestDto.setDescription(null);

        mockMvc.perform(post("/api/chamber-departmenti18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ChamberDepartmenti18nDto requestDto = createValidCreateChamberDepartmenti18nDto();
        given(chamberDepartmenti18nService.createChamberDepartmenti18n(any(ChamberDepartmenti18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ChamberDepartmenti18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/chamber-departmenti18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmenti18nDto requestDto = new ChamberDepartmenti18nDto();
        ChamberDepartmenti18nDto responseDto = new ChamberDepartmenti18nDto();
        given(chamberDepartmenti18nService.updateChamberDepartmenti18n(eq(departmentId), eq(languageId), any(ChamberDepartmenti18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/chamber-departmenti18n/{departmentId}/{languageId}", departmentId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberDepartmenti18nService).updateChamberDepartmenti18n(eq(departmentId), eq(languageId), any(ChamberDepartmenti18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmenti18nDto requestDto = new ChamberDepartmenti18nDto();
        given(chamberDepartmenti18nService.updateChamberDepartmenti18n(eq(departmentId), eq(languageId), any(ChamberDepartmenti18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChamberDepartmenti18n")
                        .message("ChamberDepartmenti18n not found")
                        .build());

        mockMvc.perform(patch("/api/chamber-departmenti18n/{departmentId}/{languageId}", departmentId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(chamberDepartmenti18nService).deleteChamberDepartmenti18n(departmentId, languageId);

        mockMvc.perform(delete("/api/chamber-departmenti18n/{departmentId}/{languageId}", departmentId, languageId))
                .andExpect(status().isNoContent());

        verify(chamberDepartmenti18nService).deleteChamberDepartmenti18n(departmentId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChamberDepartmenti18n")
                .message("ChamberDepartmenti18n not found")
                .build())
                .given(chamberDepartmenti18nService).deleteChamberDepartmenti18n(departmentId, languageId);

        mockMvc.perform(delete("/api/chamber-departmenti18n/{departmentId}/{languageId}", departmentId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ChamberDepartmenti18n.
     *
     * @return populated create request dto
     */
    private ChamberDepartmenti18nDto createValidCreateChamberDepartmenti18nDto() {
        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto();
        dto.setDepartment(new ChamberDepartmentDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("aaaaa");
        dto.setRecdeleted(true);

        return dto;
    }

}
