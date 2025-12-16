package com.wf.hackathon.cre.tools;

import com.wf.hackathon.cre.data.PropertyDataProvider;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PropertyDetailsTool {
  private final PropertyDataProvider provider;

  public PropertyDetailsTool(PropertyDataProvider provider) {
    this.provider = provider;
  }

  @Cacheable("propertyDetails")
  public Map<String, Object> fetch(String address) {
    return provider.getPropertyDetails(address);
  }
}
