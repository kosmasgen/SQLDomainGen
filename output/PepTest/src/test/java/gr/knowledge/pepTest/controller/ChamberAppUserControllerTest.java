package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import gr.knowledge.pepTest.service.ChamberAppUserService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;

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
@WebMvcTest(ChamberAppUserController.class)
class ChamberAppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ChamberAppUserService chamberAppUserService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(chamberAppUserService.getAllChamberAppUsers()).willReturn(List.of(new ChamberAppUserDto()));

        mockMvc.perform(get("/api/chamber-app-user"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberAppUserService).getAllChamberAppUsers();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(chamberAppUserService.getChamberAppUserById(id)).willReturn(new ChamberAppUserDto());

        mockMvc.perform(get("/api/chamber-app-user/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberAppUserService).getChamberAppUserById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(chamberAppUserService.getChamberAppUserById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChamberAppUser")
                        .message("ChamberAppUser not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/chamber-app-user/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ChamberAppUserDto requestDto = createValidCreateChamberAppUserDto();
        ChamberAppUserDto responseDto = new ChamberAppUserDto();
        given(chamberAppUserService.createChamberAppUser(any(ChamberAppUserDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/chamber-app-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberAppUserService).createChamberAppUser(any(ChamberAppUserDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        ChamberAppUserDto requestDto = createValidCreateChamberAppUserDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/chamber-app-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ChamberAppUserDto requestDto = createValidCreateChamberAppUserDto();
        given(chamberAppUserService.createChamberAppUser(any(ChamberAppUserDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ChamberAppUser")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/chamber-app-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ChamberAppUserDto requestDto = new ChamberAppUserDto();
        ChamberAppUserDto responseDto = new ChamberAppUserDto();
        given(chamberAppUserService.updateChamberAppUser(eq(id), any(ChamberAppUserDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/chamber-app-user/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chamberAppUserService).updateChamberAppUser(eq(id), any(ChamberAppUserDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ChamberAppUserDto requestDto = new ChamberAppUserDto();
        given(chamberAppUserService.updateChamberAppUser(eq(id), any(ChamberAppUserDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChamberAppUser")
                        .message("ChamberAppUser not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/chamber-app-user/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(chamberAppUserService).deleteChamberAppUser(id);

        mockMvc.perform(delete("/api/chamber-app-user/{id}", id))
                .andExpect(status().isNoContent());

        verify(chamberAppUserService).deleteChamberAppUser(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChamberAppUser")
                .message("ChamberAppUser not found with id: " + id)
                .build())
                .given(chamberAppUserService).deleteChamberAppUser(id);

        mockMvc.perform(delete("/api/chamber-app-user/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ChamberAppUser.
     *
     * @return populated create request dto
     */
    private ChamberAppUserDto createValidCreateChamberAppUserDto() {
        ChamberAppUserDto dto = new ChamberAppUserDto();
        dto.setChamberId(1);
        dto.setChamberAppId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setCompany(new CompanyDto());
        dto.setRecdeleted(true);

        return dto;
    }

}
