package com.healthme.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
public class SickNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Patient patient;

    @Pattern(regexp = "[1-4]{1}")
    private int insuredIn;

    @FutureOrPresent
    private LocalDate creationDate;

    @Pattern(regexp = "[0-9]{3}")
    private int statisticalNumberOfDisease;

    private LocalDate inabilityToWorkFrom;

    @Future
    private LocalDate inabilityToWorkTo;

    private int daysInHospital;

    private int doctorId;

    @Pattern(regexp = "[1-3]{1}")
    private int kinshipCode;

    @Pattern(regexp = "[1-2]{1}")
    private int medicalIndications;

    @Pattern(regexp = "[ABCDE]{1,4}")
    private String codes;
}
