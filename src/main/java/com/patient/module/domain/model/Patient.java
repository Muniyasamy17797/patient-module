package com.patient.module.domain.model;

import java.time.LocalDate;

import com.patient.module.domain.dto.Diagnoses;
import com.patient.module.domain.dto.ReferralInfo;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Embedded
    private ReferralInfo referralInfo;

    @Embedded
    private Diagnoses diagnoses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "physician_id")
    private Physician primaryPhysician;
    
}
