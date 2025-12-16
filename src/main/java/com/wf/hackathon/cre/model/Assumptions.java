package com.wf.hackathon.cre.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Assumptions(
    @NotNull @Min(0) @Max(1) Double stressVacancyAddlPct,
    @NotNull @Min(0) @Max(1) Double stressNoiDownPct,
    @NotNull @Min(0) @Max(1) Double exitCapRatePct
) {}
