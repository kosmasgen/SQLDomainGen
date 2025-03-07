package com.sqldomaingen.service.impl;

import com.sqldomaingen.dto.CourseDTO;
import com.sqldomaingen.model.Course;
import com.sqldomaingen.repository.CourseRepository;
import com.sqldomaingen.mapper.CourseMapper;
import com.sqldomaingen.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Υλοποίηση του service για την οντότητα Course.
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    /**
     * Δημιουργεί ένα νέο instance του CourseServiceImpl.
     *
     * @param courseRepository Το repository για την οντότητα Course.
     * @param courseMapper Ο mapper για την οντότητα Course.
     */
    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    /**
     * Επιστρέφει όλα τα Course ως λίστα.
     *
     * @return Λίστα με όλα τα Course ως DTOs.
     */
    @Override
    public List<CourseDTO> getAll() {
        return courseRepository.findAll().stream().map(courseMapper::toDTO).toList();
    }

    /**
     * Επιστρέφει ένα Course με το συγκεκριμένο ID.
     *
     * @param id Το ID του Course.
     * @return Το Course ως DTO.
     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα.
     */
    @Override
    public CourseDTO getById(Long id) {
        return courseMapper.toDTO(courseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found")));
    }

    /**
     * Δημιουργεί ένα νέο Course και το αποθηκεύει στη βάση.
     *
     * @param dto Τα δεδομένα του Course που θα δημιουργηθεί.
     * @return Το δημιουργηθέν Course ως DTO.
     */
    @Override
    public CourseDTO create(CourseDTO dto) {
        logger.info("Creating new Course: {}", dto);
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(dto)));
    }

    /**
     * Ενημερώνει ένα υπάρχον Course με νέα δεδομένα.
     *
     * @param id Το ID του Course που θα ενημερωθεί.
     * @param dto Τα νέα δεδομένα του Course.
     * @return Το ενημερωμένο Course ως DTO.
     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα με το συγκεκριμένο ID.
     */
    @Override
    public CourseDTO update(Long id, CourseDTO dto) {
        Course existingEntity = courseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found"));
        Course updatedEntity = courseMapper.updateEntityFromDTO(dto, existingEntity);
        return courseMapper.toDTO(courseRepository.save(updatedEntity));
    }

    /**
     * Διαγράφει ένα Course από τη βάση.
     *
     * @param id Το ID του Course που θα διαγραφεί.
     */
    @Override
    public void deleteById(Long id) {
        logger.info("Deleting Course with ID: {}", id);
        courseRepository.deleteById(id);
    }

    /**
     * Ελέγχει αν υπάρχει το Course με το συγκεκριμένο ID.
     *
     * @param id Το ID της οντότητας.
     * @return `true` αν υπάρχει, αλλιώς `false`.
     */
    @Override
    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    /**
     * Επιστρέφει το πλήθος των εγγραφών της οντότητας Course.
     *
     * @return Ο αριθμός των εγγραφών.
     */
    @Override
    public long count() {
        return courseRepository.count();
    }
}
