package com.patient.module.domain.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patient.module.domain.model.Patient;

public interface PatientJpaPort {

     Optional<Patient> findById(Long id);
     List<Patient> findAll();
     Page<Patient> findpage(Pageable pageable);
     Patient save(Patient patient);
     void delete(Long id);    

}
