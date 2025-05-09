package com.patient.module.domain.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.patient.module.domain.dto.PatientData;
import com.patient.module.domain.dto.PatientRequest;
import com.patient.module.domain.model.Patient;
import com.patient.module.domain.model.Physician;

@Mapper(componentModel = "spring", uses = {PhysicianMapper.class})
public interface PatientMapper {

     /**
     * Converts a Patient entity to a PatientInfo DTO.
     *
     * @param patient the Patient entity to convert
     * @return the corresponding PatientInfo DTO
     */
    @Mapping(source = "primaryPhysician", target = "primaryPhysician")
    PatientData toInfo(Patient patient);

     /**
     * Converts a PatientRequest DTO to a Patient entity.
     *
     * <p>This method maps the primary physician license ID from the request to the
     * corresponding Physician entity using the {@link PhysicianResolver}.</p>
     *
     * @param request           the PatientRequest DTO to convert
     * @param physicianResolver the resolver for mapping physician license IDs
     * @return the corresponding Patient entity
     */
    @Mapping(source = "primaryPhysicianLicenseId", target = "primaryPhysician")
    Patient toEntity(PatientRequest request, @Context PhysicianResolver physicianResolver);

    /**
     * Links the resolved Physician entity to the Patient entity after mapping.
     *
     * <p>This method is executed after the main mapping process to ensure that the
     * primary physician is correctly resolved and linked to the Patient entity.</p>
     *
     * @param patient           the Patient entity being mapped
     * @param request           the PatientRequest DTO being mapped
     * @param physicianResolver the resolver for mapping physician license IDs
     */
    @AfterMapping
    default void linkPhysician(@MappingTarget Patient patient, PatientRequest request, @Context PhysicianResolver physicianResolver) {
        Physician physician = physicianResolver.resolvePhysician(request.getPrimaryPhysicianLicenseId());
        if (physician == null) {
            throw new PhysicianNotFoundException("Physician not found with license number: " + request.getPrimaryPhysicianLicenseId());
        }
        patient.setPrimaryPhysician(physician);
    }

    /**
     * Maps a physician license number to a Physician entity using the resolver.
     *
     * @param licenseNumber the license number of the physician
     * @param resolver      the resolver for mapping physician license IDs
     * @return the corresponding Physician entity
     */
    default Physician map(String licenseNumber, @Context PhysicianResolver resolver) {
        return resolver.resolvePhysician(licenseNumber);
    }

    
} 