package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.UserContactinfoDto;
import gr.knowledge.pepTest.service.UserContactinfoService;
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
@WebMvcTest(UserContactinfoController.class)
class UserContactinfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserContactinfoService userContactinfoService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(userContactinfoService.getAllUserContactinfos()).willReturn(List.of(new UserContactinfoDto()));

        mockMvc.perform(get("/api/user-contactinfo"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(userContactinfoService).getAllUserContactinfos();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(userContactinfoService.getUserContactinfoById(id)).willReturn(new UserContactinfoDto());

        mockMvc.perform(get("/api/user-contactinfo/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(userContactinfoService).getUserContactinfoById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(userContactinfoService.getUserContactinfoById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("UserContactinfo")
                        .message("UserContactinfo not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/user-contactinfo/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        UserContactinfoDto requestDto = createValidCreateUserContactinfoDto();
        UserContactinfoDto responseDto = new UserContactinfoDto();
        given(userContactinfoService.createUserContactinfo(any(UserContactinfoDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/user-contactinfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(userContactinfoService).createUserContactinfo(any(UserContactinfoDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        UserContactinfoDto requestDto = createValidCreateUserContactinfoDto();
        requestDto.setUsername(null);

        mockMvc.perform(post("/api/user-contactinfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        UserContactinfoDto requestDto = createValidCreateUserContactinfoDto();
        given(userContactinfoService.createUserContactinfo(any(UserContactinfoDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("UserContactinfo")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/user-contactinfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UserContactinfoDto requestDto = new UserContactinfoDto();
        UserContactinfoDto responseDto = new UserContactinfoDto();
        given(userContactinfoService.updateUserContactinfo(eq(id), any(UserContactinfoDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/user-contactinfo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(userContactinfoService).updateUserContactinfo(eq(id), any(UserContactinfoDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UserContactinfoDto requestDto = new UserContactinfoDto();
        given(userContactinfoService.updateUserContactinfo(eq(id), any(UserContactinfoDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("UserContactinfo")
                        .message("UserContactinfo not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/user-contactinfo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(userContactinfoService).deleteUserContactinfo(id);

        mockMvc.perform(delete("/api/user-contactinfo/{id}", id))
                .andExpect(status().isNoContent());

        verify(userContactinfoService).deleteUserContactinfo(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("UserContactinfo")
                .message("UserContactinfo not found with id: " + id)
                .build())
                .given(userContactinfoService).deleteUserContactinfo(id);

        mockMvc.perform(delete("/api/user-contactinfo/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for UserContactinfo.
     *
     * @return populated create request dto
     */
    private UserContactinfoDto createValidCreateUserContactinfoDto() {
        UserContactinfoDto dto = new UserContactinfoDto();
        dto.setCompany(new CompanyDto());
        dto.setUsername("test");

        return dto;
    }

}
