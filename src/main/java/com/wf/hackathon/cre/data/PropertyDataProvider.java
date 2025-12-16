package com.wf.hackathon.cre.data;

import java.util.Map;

public interface PropertyDataProvider {
  Map<String, Object> getPropertyDetails(String address);
}
