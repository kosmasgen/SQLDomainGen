package com.sqldomaingen.service;

import com.sqldomaingen.dto.StudentDTO;
import java.util.List;

public interface StudentService {

    List<StudentDTO> getAll();
    StudentDTO getById(String id);
    StudentDTO create(StudentDTO dto);
    StudentDTO update(String id, StudentDTO dto);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}