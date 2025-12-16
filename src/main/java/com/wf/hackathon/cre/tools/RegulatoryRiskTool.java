package com.wf.hackathon.cre.tools;

import com.wf.hackathon.cre.data.RegulatoryRiskProvider;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RegulatoryRiskTool {
  private final RegulatoryRiskProvider provider;

  public RegulatoryRiskTool(RegulatoryRiskProvider provider) {
    this.provider = provider;
  }

  @Cacheable("regulatoryRisk")
  public Map<String, Object> fetch(String address) {
    return provider.getRegulatoryRisk(address);
  }
}
