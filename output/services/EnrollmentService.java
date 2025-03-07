package com.sqldomaingen.service;

import com.sqldomaingen.dto.EnrollmentDTO;
import java.util.List;

public interface EnrollmentService {

    List<EnrollmentDTO> getAll();
    EnrollmentDTO getById(Long id);
    EnrollmentDTO create(EnrollmentDTO dto);
    EnrollmentDTO update(Long id, EnrollmentDTO dto);
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}