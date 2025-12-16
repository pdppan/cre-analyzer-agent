package com.wf.hackathon.cre.data;

import java.util.Map;

public interface DemographicsProvider {
  Map<String, Object> getDemographics(String address);
}
