package com.patient.module.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Embeddable
public class Diagnoses implements java.io.Serializable {

    @JsonIgnore
    @Transient
    private static final long serialVersionUID = 1L;
    
    private String primaryDiagnosis;
    private String secondDiagnosis;
    private String thirdDiagnosis;

}
