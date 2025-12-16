package com.wf.hackathon.cre.tools;

import com.wf.hackathon.cre.data.MarketDataProvider;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MarketTrendsTool {
  private final MarketDataProvider provider;

  public MarketTrendsTool(MarketDataProvider provider) {
    this.provider = provider;
  }

  @Cacheable("marketTrends")
  public Map<String, Object> fetch(String address, String propertyType) {
    return provider.getMarketTrends(address, propertyType);
  }
}
