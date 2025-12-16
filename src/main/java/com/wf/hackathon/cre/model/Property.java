package com.wf.hackathon.cre.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Property(
    @NotBlank String address,
    @NotBlank String propertyType,
    @NotNull @Min(1800) @Max(2100) Integer yearBuilt,
    @NotNull @Min(1) Integer netRentableSqFt,
    @NotNull @Min(0) @Max(1) Double occupancyPct,
    @NotNull @Min(0) Double noiAnnual,
    @NotNull @Min(0) Double capexHoldback
) {}
