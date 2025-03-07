package com.sqldomaingen.service.impl;

import com.sqldomaingen.dto.EnrollmentDTO;
import com.sqldomaingen.model.Enrollment;
import com.sqldomaingen.repository.EnrollmentRepository;
import com.sqldomaingen.mapper.EnrollmentMapper;
import com.sqldomaingen.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Υλοποίηση του service για την οντότητα Enrollment.
 */
@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentServiceImpl.class);
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    /**
     * Δημιουργεί ένα νέο instance του EnrollmentServiceImpl.
     *
     * @param enrollmentRepository Το repository για την οντότητα Enrollment.
     * @param enrollmentMapper Ο mapper για την οντότητα Enrollment.
     */
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    /**
     * Επιστρέφει όλα τα Enrollment ως λίστα.
     *
     * @return Λίστα με όλα τα Enrollment ως DTOs.
     */
    @Override
    public List<EnrollmentDTO> getAll() {
        return enrollmentRepository.findAll().stream().map(enrollmentMapper::toDTO).toList();
    }

    /**
     * Επιστρέφει ένα Enrollment με το συγκεκριμένο ID.
     *
     * @param id Το ID του Enrollment.
     * @return Το Enrollment ως DTO.
     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα.
     */
    @Override
    public EnrollmentDTO getById(Long id) {
        return enrollmentMapper.toDTO(enrollmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found")));
    }

    /**
     * Δημιουργεί ένα νέο Enrollment και το αποθηκεύει στη βάση.
     *
     * @param dto Τα δεδομένα του Enrollment που θα δημιουργηθεί.
     * @return Το δημιουργηθέν Enrollment ως DTO.
     */
    @Override
    public EnrollmentDTO create(EnrollmentDTO dto) {
        logger.info("Creating new Enrollment: {}", dto);
        return enrollmentMapper.toDTO(enrollmentRepository.save(enrollmentMapper.toEntity(dto)));
    }

    /**
     * Ενημερώνει ένα υπάρχον Enrollment με νέα δεδομένα.
     *
     * @param id Το ID του Enrollment που θα ενημερωθεί.
     * @param dto Τα νέα δεδομένα του Enrollment.
     * @return Το ενημερωμένο Enrollment ως DTO.
     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα με το συγκεκριμένο ID.
     */
    @Override
    public EnrollmentDTO update(Long id, EnrollmentDTO dto) {
        Enrollment existingEntity = enrollmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found"));
        Enrollment updatedEntity = enrollmentMapper.updateEntityFromDTO(dto, existingEntity);
        return enrollmentMapper.toDTO(enrollmentRepository.save(updatedEntity));
    }

    /**
     * Διαγράφει ένα Enrollment από τη βάση.
     *
     * @param id Το ID του Enrollment που θα διαγραφεί.
     */
    @Override
    public void deleteById(Long id) {
        logger.info("Deleting Enrollment with ID: {}", id);
        enrollmentRepository.deleteById(id);
    }

    /**
     * Ελέγχει αν υπάρχει το Enrollment με το συγκεκριμένο ID.
     *
     * @param id Το ID της οντότητας.
     * @return `true` αν υπάρχει, αλλιώς `false`.
     */
    @Override
    public boolean existsById(Long id) {
        return enrollmentRepository.existsById(id);
    }

    /**
     * Επιστρέφει το πλήθος των εγγραφών της οντότητας Enrollment.
     *
     * @return Ο αριθμός των εγγραφών.
     */
    @Override
    public long count() {
        return enrollmentRepository.count();
    }
}
