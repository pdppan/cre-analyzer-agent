package com.wf.hackathon.cre.data;

import java.util.Map;

/** TODO: wire FEMA flood + local zoning/environmental endpoints. */
public class HttpRegulatoryRiskProvider implements RegulatoryRiskProvider {
  @Override
  public Map<String, Object> getRegulatoryRisk(String address) {
    return Map.of("notes", "HttpRegulatoryRiskProvider not implemented. Enable mockMode=true or wire endpoints.");
  }
}
