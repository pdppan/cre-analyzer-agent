package com.wf.hackathon.cre.data;

import java.util.Map;

/** TODO: wire market trend feeds (public proxies or private market data). */
public class HttpMarketDataProvider implements MarketDataProvider {
  @Override
  public Map<String, Object> getMarketTrends(String address, String propertyType) {
    return Map.of("notes", "HttpMarketDataProvider not implemented. Enable mockMode=true or wire endpoints.");
  }
}
