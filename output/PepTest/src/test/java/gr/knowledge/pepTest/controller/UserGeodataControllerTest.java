package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.UserGeodataDto;
import gr.knowledge.pepTest.service.UserGeodataService;
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
@WebMvcTest(UserGeodataController.class)
class UserGeodataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserGeodataService userGeodataService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(userGeodataService.getAllUserGeodatas()).willReturn(List.of(new UserGeodataDto()));

        mockMvc.perform(get("/api/user-geodata"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(userGeodataService).getAllUserGeodatas();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(userGeodataService.getUserGeodataById(id)).willReturn(new UserGeodataDto());

        mockMvc.perform(get("/api/user-geodata/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(userGeodataService).getUserGeodataById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(userGeodataService.getUserGeodataById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("UserGeodata")
                        .message("UserGeodata not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/user-geodata/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        UserGeodataDto requestDto = createValidCreateUserGeodataDto();
        UserGeodataDto responseDto = new UserGeodataDto();
        given(userGeodataService.createUserGeodata(any(UserGeodataDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/user-geodata")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(userGeodataService).createUserGeodata(any(UserGeodataDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        UserGeodataDto requestDto = createValidCreateUserGeodataDto();
        requestDto.setUsername(null);

        mockMvc.perform(post("/api/user-geodata")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        UserGeodataDto requestDto = createValidCreateUserGeodataDto();
        given(userGeodataService.createUserGeodata(any(UserGeodataDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("UserGeodata")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/user-geodata")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UserGeodataDto requestDto = new UserGeodataDto();
        UserGeodataDto responseDto = new UserGeodataDto();
        given(userGeodataService.updateUserGeodata(eq(id), any(UserGeodataDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/user-geodata/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(userGeodataService).updateUserGeodata(eq(id), any(UserGeodataDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UserGeodataDto requestDto = new UserGeodataDto();
        given(userGeodataService.updateUserGeodata(eq(id), any(UserGeodataDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("UserGeodata")
                        .message("UserGeodata not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/user-geodata/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(userGeodataService).deleteUserGeodata(id);

        mockMvc.perform(delete("/api/user-geodata/{id}", id))
                .andExpect(status().isNoContent());

        verify(userGeodataService).deleteUserGeodata(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("UserGeodata")
                .message("UserGeodata not found with id: " + id)
                .build())
                .given(userGeodataService).deleteUserGeodata(id);

        mockMvc.perform(delete("/api/user-geodata/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for UserGeodata.
     *
     * @return populated create request dto
     */
    private UserGeodataDto createValidCreateUserGeodataDto() {
        UserGeodataDto dto = new UserGeodataDto();
        dto.setCompany(new CompanyDto());
        dto.setUsername("test");
        dto.setLatitude("aaaaa");
        dto.setLongitude("aaaaa");

        return dto;
    }

}
