package com.patient.module.domain.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PatientRequest {

    private String medicalRecordNumber;
    private LocalDate startOfCareDate;
    private String status;
    private String firstName;
    private String lastName;
    private String sex;
    private LocalDate birthDate;
    private String maritalStatus;
    private String address;
    private String city;
    private String state;
    private String county;
    private String zipCode;
    private String email;
    private String mobile;
    private ReferralInfo referralInfo;
    private Diagnoses diagnoses;
    private String primaryPhysicianLicenseId;

}
