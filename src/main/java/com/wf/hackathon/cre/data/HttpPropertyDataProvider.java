package com.wf.hackathon.cre.data;

import java.util.Map;

/** TODO: wire real parcel/GIS/geocoding endpoints. */
public class HttpPropertyDataProvider implements PropertyDataProvider {
  @Override
  public Map<String, Object> getPropertyDetails(String address) {
    return Map.of("notes", "HttpPropertyDataProvider not implemented. Enable mockMode=true or wire endpoints.");
  }
}
