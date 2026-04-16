package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.WorkingHoursDto;
import gr.knowledge.pepTest.service.WorkingHoursService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;

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
@WebMvcTest(WorkingHoursController.class)
class WorkingHoursControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private WorkingHoursService workingHoursService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(workingHoursService.getAllWorkingHourses()).willReturn(List.of(new WorkingHoursDto()));

        mockMvc.perform(get("/api/working-hours"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(workingHoursService).getAllWorkingHourses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        Long id = 1L;
        given(workingHoursService.getWorkingHoursById(id)).willReturn(new WorkingHoursDto());

        mockMvc.perform(get("/api/working-hours/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(workingHoursService).getWorkingHoursById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        Long id = 1L;
        given(workingHoursService.getWorkingHoursById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("WorkingHours")
                        .message("WorkingHours not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/working-hours/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        WorkingHoursDto requestDto = createValidCreateWorkingHoursDto();
        WorkingHoursDto responseDto = new WorkingHoursDto();
        given(workingHoursService.createWorkingHours(any(WorkingHoursDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/working-hours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(workingHoursService).createWorkingHours(any(WorkingHoursDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        WorkingHoursDto requestDto = createValidCreateWorkingHoursDto();
        requestDto.setDayOfWeek(null);

        mockMvc.perform(post("/api/working-hours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        WorkingHoursDto requestDto = createValidCreateWorkingHoursDto();
        given(workingHoursService.createWorkingHours(any(WorkingHoursDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("WorkingHours")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/working-hours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        Long id = 1L;
        WorkingHoursDto requestDto = new WorkingHoursDto();
        WorkingHoursDto responseDto = new WorkingHoursDto();
        given(workingHoursService.updateWorkingHours(eq(id), any(WorkingHoursDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/working-hours/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(workingHoursService).updateWorkingHours(eq(id), any(WorkingHoursDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        Long id = 1L;
        WorkingHoursDto requestDto = new WorkingHoursDto();
        given(workingHoursService.updateWorkingHours(eq(id), any(WorkingHoursDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("WorkingHours")
                        .message("WorkingHours not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/working-hours/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        Long id = 1L;
        willDoNothing().given(workingHoursService).deleteWorkingHours(id);

        mockMvc.perform(delete("/api/working-hours/{id}", id))
                .andExpect(status().isNoContent());

        verify(workingHoursService).deleteWorkingHours(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        Long id = 1L;
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("WorkingHours")
                .message("WorkingHours not found with id: " + id)
                .build())
                .given(workingHoursService).deleteWorkingHours(id);

        mockMvc.perform(delete("/api/working-hours/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for WorkingHours.
     *
     * @return populated create request dto
     */
    private WorkingHoursDto createValidCreateWorkingHoursDto() {
        WorkingHoursDto dto = new WorkingHoursDto();
        dto.setCompany(new CompanyDto());
        dto.setDayOfWeek("aaaaa");
        dto.setIsClosed(true);
        dto.setProfile(new CompanyProfileDto());

        return dto;
    }

}
