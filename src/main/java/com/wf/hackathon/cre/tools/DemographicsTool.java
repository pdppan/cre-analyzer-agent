package com.wf.hackathon.cre.tools;

import com.wf.hackathon.cre.data.DemographicsProvider;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DemographicsTool {
  private final DemographicsProvider provider;

  public DemographicsTool(DemographicsProvider provider) {
    this.provider = provider;
  }

  @Cacheable("demographics")
  public Map<String, Object> fetch(String address) {
    return provider.getDemographics(address);
  }
}
