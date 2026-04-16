package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.FolderDto;
import gr.knowledge.pepTest.service.FolderService;
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
@WebMvcTest(FolderController.class)
class FolderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FolderService folderService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(folderService.getAllFolders()).willReturn(List.of(new FolderDto()));

        mockMvc.perform(get("/api/folder"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(folderService).getAllFolders();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(folderService.getFolderById(id)).willReturn(new FolderDto());

        mockMvc.perform(get("/api/folder/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(folderService).getFolderById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(folderService.getFolderById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Folder")
                        .message("Folder not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/folder/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        FolderDto requestDto = createValidCreateFolderDto();
        FolderDto responseDto = new FolderDto();
        given(folderService.createFolder(any(FolderDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/folder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(folderService).createFolder(any(FolderDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        FolderDto requestDto = createValidCreateFolderDto();
        requestDto.setDescr(null);

        mockMvc.perform(post("/api/folder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        FolderDto requestDto = createValidCreateFolderDto();
        given(folderService.createFolder(any(FolderDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("Folder")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/folder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        FolderDto requestDto = new FolderDto();
        FolderDto responseDto = new FolderDto();
        given(folderService.updateFolder(eq(id), any(FolderDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/folder/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(folderService).updateFolder(eq(id), any(FolderDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        FolderDto requestDto = new FolderDto();
        given(folderService.updateFolder(eq(id), any(FolderDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Folder")
                        .message("Folder not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/folder/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(folderService).deleteFolder(id);

        mockMvc.perform(delete("/api/folder/{id}", id))
                .andExpect(status().isNoContent());

        verify(folderService).deleteFolder(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Folder")
                .message("Folder not found with id: " + id)
                .build())
                .given(folderService).deleteFolder(id);

        mockMvc.perform(delete("/api/folder/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for Folder.
     *
     * @return populated create request dto
     */
    private FolderDto createValidCreateFolderDto() {
        FolderDto dto = new FolderDto();
        dto.setDescr("test");
        dto.setUri("aaaaa");
        dto.setRecdeleted(true);

        return dto;
    }

}
