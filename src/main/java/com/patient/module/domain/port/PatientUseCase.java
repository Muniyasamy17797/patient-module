package com.patient.module.domain.port;

import java.util.List;

import com.patient.module.domain.dto.PagedResponse;
import com.patient.module.domain.dto.PatientData;
import com.patient.module.domain.dto.PatientRequest;

public interface PatientUseCase {

    PatientData create(PatientRequest request);

    PatientData update(Long id, PatientRequest request);

    PatientData getById(Long id);

    List<PatientData> getAll();

    PagedResponse<PatientData> getAllPaged(int offset, int limit);

    void delete(Long id);
    
}
