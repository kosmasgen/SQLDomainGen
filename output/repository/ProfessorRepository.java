package com.sqldomaingen.repository;

import com.sqldomaingen.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID> {

}
