package com.patient.module.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Data Transfer Object for Patient.
 * This class is used to transfer patient data between layers.
 */
@Data
public class PhysicianInfo  implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String contactNumber;
    private String secondaryContactNumber;
    private String email;
    private String specialization;
    private String licenseNumber;
    private String hospital;
    private String officeAddress;
    private int yearsOfExperience;
    private String status;

}
