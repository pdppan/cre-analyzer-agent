package com.wf.hackathon.cre.model;

public record DealMemo(
    String executiveSummary,
    String propertyOverview,
    String marketAndDemographics,
    String regulatoryAndEnvironmentalRisk,
    String financialScenarios,
    String collateralValuation,
    String recommendation
) {}
