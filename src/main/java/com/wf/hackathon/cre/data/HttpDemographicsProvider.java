package com.wf.hackathon.cre.data;

import java.util.Map;

/** TODO: wire Census ACS / BLS endpoints. */
public class HttpDemographicsProvider implements DemographicsProvider {
  @Override
  public Map<String, Object> getDemographics(String address) {
    return Map.of("notes", "HttpDemographicsProvider not implemented. Enable mockMode=true or wire endpoints.");
  }
}
