package com.patient.module.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 * Data Transfer Object for Referral Information.
 * This class is used to transfer referral information data between layers.
 */
@Data
@Embeddable
public class ReferralInfo implements java.io.Serializable {

    @JsonIgnore
    @Transient
    private static final long serialVersionUID = 1L;

    private String referrerName;
    @Column(name = "referrer_email")
    private String email;
    @Column(name = "referrer_mobile")
    private String mobile;

}
