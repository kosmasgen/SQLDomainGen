package com.sqldomaingen.service.impl;

import com.sqldomaingen.dto.ProfessorDTO;
import com.sqldomaingen.model.Professor;
import com.sqldomaingen.repository.ProfessorRepository;
import com.sqldomaingen.mapper.ProfessorMapper;
import com.sqldomaingen.service.ProfessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Υλοποίηση του service για την οντότητα Professor.
 */
@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    private static final Logger logger = LoggerFactory.getLogger(ProfessorServiceImpl.class);
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    /**
     * Δημιουργεί ένα νέο instance του ProfessorServiceImpl.
     *
     * @param professorRepository Το repository για την οντότητα Professor.
     * @param professorMapper Ο mapper για την οντότητα Professor.
     */
    public ProfessorServiceImpl(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    /**
     * Επιστρέφει όλα τα Professor ως λίστα.
     *
     * @return Λίστα με όλα τα Professor ως DTOs.
     */
    @Override
    public List<ProfessorDTO> getAll() {
        return professorRepository.findAll().stream().map(professorMapper::toDTO).toList();
    }

    /**
     * Επιστρέφει ένα Professor με το συγκεκριμένο ID.
     *
     * @param id Το ID του Professor.
     * @return Το Professor ως DTO.
     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα.
     */
    @Override
    public ProfessorDTO getById(UUID id) {
        return professorMapper.toDTO(professorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found")));
    }

    /**
     * Δημιουργεί ένα νέο Professor και το αποθηκεύει στη βάση.
     *
     * @param dto Τα δεδομένα του Professor που θα δημιουργηθεί.
     * @return Το δημιουργηθέν Professor ως DTO.
     */
    @Override
    public ProfessorDTO create(ProfessorDTO dto) {
        logger.info("Creating new Professor: {}", dto);
        return professorMapper.toDTO(professorRepository.save(professorMapper.toEntity(dto)));
    }

    /**
     * Ενημερώνει ένα υπάρχον Professor με νέα δεδομένα.
     *
     * @param id Το ID του Professor που θα ενημερωθεί.
     * @param dto Τα νέα δεδομένα του Professor.
     * @return Το ενημερωμένο Professor ως DTO.
     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα με το συγκεκριμένο ID.
     */
    @Override
    public ProfessorDTO update(UUID id, ProfessorDTO dto) {
        Professor existingEntity = professorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found"));
        Professor updatedEntity = professorMapper.updateEntityFromDTO(dto, existingEntity);
        return professorMapper.toDTO(professorRepository.save(updatedEntity));
    }

    /**
     * Διαγράφει ένα Professor από τη βάση.
     *
     * @param id Το ID του Professor που θα διαγραφεί.
     */
    @Override
    public void deleteById(UUID id) {
        logger.info("Deleting Professor with ID: {}", id);
        professorRepository.deleteById(id);
    }

    /**
     * Ελέγχει αν υπάρχει το Professor με το συγκεκριμένο ID.
     *
     * @param id Το ID της οντότητας.
     * @return `true` αν υπάρχει, αλλιώς `false`.
     */
    @Override
    public boolean existsById(UUID id) {
        return professorRepository.existsById(id);
    }

    /**
     * Επιστρέφει το πλήθος των εγγραφών της οντότητας Professor.
     *
     * @return Ο αριθμός των εγγραφών.
     */
    @Override
    public long count() {
        return professorRepository.count();
    }
}
