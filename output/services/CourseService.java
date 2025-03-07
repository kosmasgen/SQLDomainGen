package com.sqldomaingen.service;

import com.sqldomaingen.dto.CourseDTO;
import java.util.List;

public interface CourseService {

    List<CourseDTO> getAll();
    CourseDTO getById(Long id);
    CourseDTO create(CourseDTO dto);
    CourseDTO update(Long id, CourseDTO dto);
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}