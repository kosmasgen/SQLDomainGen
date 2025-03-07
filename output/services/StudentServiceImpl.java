package com.sqldomaingen.service.impl;

import com.sqldomaingen.dto.StudentDTO;
import com.sqldomaingen.model.Student;
import com.sqldomaingen.repository.StudentRepository;
import com.sqldomaingen.mapper.StudentMapper;
import com.sqldomaingen.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Υλοποίηση του service για την οντότητα Student.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /**
     * Δημιουργεί ένα νέο instance του StudentServiceImpl.
     *
     * @param studentRepository Το repository για την οντότητα Student.
     * @param studentMapper Ο mapper για την οντότητα Student.
     */
    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    /**
     * Επιστρέφει όλα τα Student ως λίστα.
     *
     * @return Λίστα με όλα τα Student ως DTOs.
     */
    @Override
    public List<StudentDTO> getAll() {
        return studentRepository.findAll().stream().map(studentMapper::toDTO).toList();
    }

    /**
     * Επιστρέφει ένα Student με το συγκεκριμένο ID.
     *
     * @param id Το ID του Student.
     * @return Το Student ως DTO.
     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα.
     */
    @Override
    public StudentDTO getById(String id) {
        return studentMapper.toDTO(studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found")));
    }

    /**
     * Δημιουργεί ένα νέο Student και το αποθηκεύει στη βάση.
     *
     * @param dto Τα δεδομένα του Student που θα δημιουργηθεί.
     * @return Το δημιουργηθέν Student ως DTO.
     */
    @Override
    public StudentDTO create(StudentDTO dto) {
        logger.info("Creating new Student: {}", dto);
        return studentMapper.toDTO(studentRepository.save(studentMapper.toEntity(dto)));
    }

    /**
     * Ενημερώνει ένα υπάρχον Student με νέα δεδομένα.
     *
     * @param id Το ID του Student που θα ενημερωθεί.
     * @param dto Τα νέα δεδομένα του Student.
     * @return Το ενημερωμένο Student ως DTO.
     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα με το συγκεκριμένο ID.
     */
    @Override
    public StudentDTO update(String id, StudentDTO dto) {
        Student existingEntity = studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found"));
        Student updatedEntity = studentMapper.updateEntityFromDTO(dto, existingEntity);
        return studentMapper.toDTO(studentRepository.save(updatedEntity));
    }

    /**
     * Διαγράφει ένα Student από τη βάση.
     *
     * @param id Το ID του Student που θα διαγραφεί.
     */
    @Override
    public void deleteById(String id) {
        logger.info("Deleting Student with ID: {}", id);
        studentRepository.deleteById(id);
    }

    /**
     * Ελέγχει αν υπάρχει το Student με το συγκεκριμένο ID.
     *
     * @param id Το ID της οντότητας.
     * @return `true` αν υπάρχει, αλλιώς `false`.
     */
    @Override
    public boolean existsById(String id) {
        return studentRepository.existsById(id);
    }

    /**
     * Επιστρέφει το πλήθος των εγγραφών της οντότητας Student.
     *
     * @return Ο αριθμός των εγγραφών.
     */
    @Override
    public long count() {
        return studentRepository.count();
    }
}
