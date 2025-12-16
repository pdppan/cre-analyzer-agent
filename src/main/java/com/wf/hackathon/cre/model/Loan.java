package com.wf.hackathon.cre.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Loan(
    @NotNull @Min(1) Double requestedLoanAmount,
    @NotNull @Min(0) @Max(1) Double interestRatePct,
    @NotNull @Min(1) Integer amortizationYears,
    @NotNull @Min(1) Integer termYears,
    @NotNull @Min(0) Double dscrCovenant
) {}
