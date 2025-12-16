package com.wf.hackathon.cre.data;

import java.util.Map;

public interface MarketDataProvider {
  Map<String, Object> getMarketTrends(String address, String propertyType);
}
