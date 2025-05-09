package com.patient.module.domain.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.patient.module.adoptor.out.repository.PhysicianRepository;
import com.patient.module.domain.model.Physician;

@Component
public class PhysicianResolver {

        private final PhysicianRepository repository;

    /**
     * Constructs an instance of {@link PhysicianResolver}.
     *
     * @param repository the repository for accessing physician data
     */
    public PhysicianResolver(PhysicianRepository repository) {
        this.repository = repository;
    }

    /**
     * Resolves a physician by license number.
     *
     * <p>This method retrieves a physician entity from the database based on the provided
     * license number. If the license number is null or the physician is not found, it
     * returns null or throws an exception, respectively.</p>
     *
     * @param licenseNumber the license number of the physician
     * @return the resolved physician entity
     * @throws PhysicianNotFoundException if no physician is found with the given license number
     */
    @Named("resolvePhysician")
    public Physician resolvePhysician(String licenseNumber) {
        return licenseNumber != null
            ? repository.findByLicenseNumber(licenseNumber)
            .orElseThrow(() -> new PhysicianNotFoundException("Physician not found with license number: " + licenseNumber))
            : null;
    }
    
}


