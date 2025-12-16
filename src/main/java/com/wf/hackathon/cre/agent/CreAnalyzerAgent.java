package com.wf.hackathon.cre.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wf.hackathon.cre.llm.LlmClient;
import com.wf.hackathon.cre.llm.PromptTemplates;
import com.wf.hackathon.cre.model.*;
import com.wf.hackathon.cre.tools.*;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreAnalyzerAgent {

  private final ObjectMapper om = new ObjectMapper();

  private final LlmClient llm;
  private final PropertyDetailsTool propertyDetailsTool;
  private final MarketTrendsTool marketTrendsTool;
  private final DemographicsTool demographicsTool;
  private final RegulatoryRiskTool regulatoryRiskTool;
  private final FinancialScenariosTool financialScenariosTool;
  private final CollateralValuationTool collateralValuationTool;

  public CreAnalyzerAgent(
      LlmClient llm,
      PropertyDetailsTool propertyDetailsTool,
      MarketTrendsTool marketTrendsTool,
      DemographicsTool demographicsTool,
      RegulatoryRiskTool regulatoryRiskTool,
      FinancialScenariosTool financialScenariosTool,
      CollateralValuationTool collateralValuationTool
  ) {
    this.llm = llm;
    this.propertyDetailsTool = propertyDetailsTool;
    this.marketTrendsTool = marketTrendsTool;
    this.demographicsTool = demographicsTool;
    this.regulatoryRiskTool = regulatoryRiskTool;
    this.financialScenariosTool = financialScenariosTool;
    this.collateralValuationTool = collateralValuationTool;
  }

  public DealMemoResponse analyze(DealRequest req) {
    try {
      String dealJson = om.writerWithDefaultPrettyPrinter().writeValueAsString(req);

      // 1) Planner step (LLM returns JSON)
      String planRaw = llm.generateText(PromptTemplates.plannerPrompt(dealJson));
      Map<String, Object> plan = safeParseJson(planRaw);

      // 2) Execute tools requested by plan (defaults to true if uncertain)
      Map<String, Object> toolContext = new HashMap<>();

      boolean doProperty = bool(plan.getOrDefault("propertyDetails", true));
      boolean doMarket = bool(plan.getOrDefault("marketTrends", true));
      boolean doDemo = bool(plan.getOrDefault("demographics", true));
      boolean doReg = bool(plan.getOrDefault("regulatoryRisk", true));
      boolean doFin = bool(plan.getOrDefault("financialScenarios", true));
      boolean doVal = bool(plan.getOrDefault("collateralValuation", true));

      String address = req.property().address();
      String propertyType = req.property().propertyType();

      if (doProperty) toolContext.put("propertyDetails", propertyDetailsTool.fetch(address));
      if (doMarket) toolContext.put("marketTrends", marketTrendsTool.fetch(address, propertyType));
      if (doDemo) toolContext.put("demographics", demographicsTool.fetch(address));
      if (doReg) toolContext.put("regulatoryRisk", regulatoryRiskTool.fetch(address));
      if (doFin) toolContext.put("financialScenarios", financialScenariosTool.run(req));
      if (doVal) toolContext.put("collateralValuation", collateralValuationTool.estimate(req));

      toolContext.put("plannerOutput", plan);

      String toolContextJson = om.writerWithDefaultPrettyPrinter().writeValueAsString(toolContext);

      // 3) Memo synthesis
      String memoText = llm.generateText(PromptTemplates.memoPrompt(dealJson, toolContextJson));
      DealMemo memo = MemoParser.parseToSections(memoText);

      return new DealMemoResponse(req.dealId(), OffsetDateTime.now(), memo);
    } catch (Exception e) {
      throw new RuntimeException("CRE analysis failed: " + e.getMessage(), e);
    }
  }

  private Map<String, Object> safeParseJson(String maybeJson) {
    try {
      return om.readValue(maybeJson, new TypeReference<>() {});
    } catch (Exception ignored) {
      int start = maybeJson.indexOf('{');
      int end = maybeJson.lastIndexOf('}');
      if (start >= 0 && end > start) {
        try {
          String slice = maybeJson.substring(start, end + 1);
          return om.readValue(slice, new TypeReference<>() {});
        } catch (Exception ignored2) {
          return Map.of("raw", maybeJson);
        }
      }
      return Map.of("raw", maybeJson);
    }
  }

  private boolean bool(Object o) {
    if (o instanceof Boolean b) return b;
    if (o instanceof String s) return Boolean.parseBoolean(s.trim());
    if (o instanceof Number n) return n.intValue() != 0;
    return false;
  }
}
