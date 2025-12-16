package com.wf.hackathon.cre.data;

import java.util.Map;

public interface RegulatoryRiskProvider {
  Map<String, Object> getRegulatoryRisk(String address);
}
