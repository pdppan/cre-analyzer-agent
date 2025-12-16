package com.wf.hackathon.cre.data;

import com.wf.hackathon.cre.config.AppConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProvidersConfig {

  @Bean
  public PropertyDataProvider propertyDataProvider(AppConfig cfg) {
    if (!cfg.mockMode()) return new HttpPropertyDataProvider();
    return address -> {
      Map<String, Object> m = new HashMap<>();
      m.put("geocode", Map.of("lat", 35.2269, "lon", -80.8433));
      m.put("parcelId", "MOCK-PARCEL-CLT-0001");
      m.put("submarket", "Charlotte CBD");
      m.put("notes", "Mock property facts. Wire to county GIS/assessor where available.");
      return m;
    };
  }

  @Bean
  public MarketDataProvider marketDataProvider(AppConfig cfg) {
    if (!cfg.mockMode()) return new HttpMarketDataProvider();
    return (address, propertyType) -> Map.of(
        "market", "Charlotte CBD",
        "propertyType", propertyType,
        "vacancyPct", 0.19,
        "rentGrowthYoY", 0.01,
        "absorptionSqFtYoY", -25000,
        "sources", new String[]{"Mock: replace with CoStar/REIS/internal feeds or public proxies"}
    );
  }

  @Bean
  public DemographicsProvider demographicsProvider(AppConfig cfg) {
    if (!cfg.mockMode()) return new HttpDemographicsProvider();
    return address -> Map.of(
        "radiusMiles", 3,
        "population", 98000,
        "medianHouseholdIncome", 76000,
        "employmentGrowthYoY", 0.015,
        "notes", "Mock demographics. Consider Census ACS + BLS local area stats."
    );
  }

  @Bean
  public RegulatoryRiskProvider regulatoryRiskProvider(AppConfig cfg) {
    if (!cfg.mockMode()) return new HttpRegulatoryRiskProvider();
    return address -> Map.of(
        "femaFloodZone", "X (mock)",
        "environmentalFlags", new String[]{"None identified (mock)"},
        "zoning", "Assumed compliant (mock) - verify with local zoning maps",
        "notes", "Mock regulatory risk. Integrate FEMA NFHL, state env DBs, local zoning."
    );
  }
}
