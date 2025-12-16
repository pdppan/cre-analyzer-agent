package com.wf.hackathon.cre.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DealRequest(
    @NotBlank String dealId,
    @NotBlank String borrowerName,
    @NotNull @Valid Property property,
    @NotNull @Valid Loan loan,
    @NotNull @Valid Assumptions assumptions
) {}
