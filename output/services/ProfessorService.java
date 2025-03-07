package com.sqldomaingen.service;

import com.sqldomaingen.dto.ProfessorDTO;
import java.util.UUID;
import java.util.List;

public interface ProfessorService {

    List<ProfessorDTO> getAll();
    ProfessorDTO getById(UUID id);
    ProfessorDTO create(ProfessorDTO dto);
    ProfessorDTO update(UUID id, ProfessorDTO dto);
    void deleteById(UUID id);
    boolean existsById(UUID id);
    long count();
}