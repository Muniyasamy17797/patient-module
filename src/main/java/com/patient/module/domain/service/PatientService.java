package com.patient.module.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.patient.module.adoptor.out.repository.PatientRepository;
import com.patient.module.domain.dto.PagedResponse;
import com.patient.module.domain.dto.PatientData;
import com.patient.module.domain.dto.PatientRequest;
import com.patient.module.domain.mapper.PatientMapper;
import com.patient.module.domain.mapper.PhysicianResolver;
import com.patient.module.domain.model.Patient;
import com.patient.module.domain.port.PatientJpaPort;
import com.patient.module.domain.port.PatientUseCase;

@Service
public class PatientService implements PatientUseCase {


    private final PatientJpaPort patientJpaPort;
    private final PatientMapper mapper;
    private final PhysicianResolver physicianResolver;

    public PatientService(PatientJpaPort patientJpaPort, PatientMapper mapper, PhysicianResolver physicianResolver) {
        this.patientJpaPort = patientJpaPort;
        this.mapper = mapper;
        this.physicianResolver = physicianResolver;
    }

    /**
     * Creates a new patient.
     *
     * @param request the patient request containing patient details
     * @return the created patient information
     */
    @Override
    public PatientData create(PatientRequest request) {
        Patient patient = mapper.toEntity(request, physicianResolver);
        return mapper.toInfo(patientJpaPort.save(patient));
    }

    /**
     * Updates an existing patient.
     *
     * @param id      the ID of the patient to update
     * @param request the patient request containing updated details
     * @return the updated patient information
     */
    @Override
    @CachePut(value = "patients", key = "#id")
    public PatientData update(Long id, PatientRequest request) {
        Patient existing = patientJpaPort.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        Patient updated = mapper.toEntity(request, physicianResolver);
        updated.setId(existing.getId());
        return mapper.toInfo(patientJpaPort.save(updated));
    }

    /**
     * Retrieves a patient by ID.
     *
     * @param id the ID of the patient to retrieve
     * @return the patient information
     */
    @Cacheable(value = "patients", key = "#id")
    @Override
    public PatientData getById(Long id) {
        return mapper.toInfo(patientJpaPort.findById(id).orElseThrow(() -> new RuntimeException("Patient not found")));
    }

    /**
     * Retrieves all patients.
     *
     * @return a list of all patients
     */
    @Override
    public List<PatientData> getAll() {
        return patientJpaPort.findAll().stream().map(mapper::toInfo).collect(Collectors.toList());
    }

    /**
     * Retrieves a paginated list of roles.
     *
     * @param offset the starting index of the page
     * @param limit  the number of roles per page
     * @return a PagedResponse containing the paginated RoleInfo objects
     */
    public PagedResponse<PatientData> getAllPaged(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset / limit, limit);
        Page<Patient> page = patientJpaPort.findpage(pageRequest);

        List<PatientData> patientInfos = page.getContent().stream()
            .map(mapper::toInfo)
            .toList();

        return new PagedResponse<>(
        patientInfos,
        page.getTotalElements(),
        page.getNumber(),
        page.getSize()
    );
        
    }

    /**
     * Deletes a patient by ID.
     *
     * @param id the ID of the patient to delete
     */
    @Override
    @CacheEvict(value = "patients", key = "#id")
    public void delete(Long id) {
        patientJpaPort.delete(id);
    }
    
}
