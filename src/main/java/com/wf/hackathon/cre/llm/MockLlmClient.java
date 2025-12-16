package com.wf.hackathon.cre.llm;

//import org.springframework.stereotype.Component;

//@Component
public class MockLlmClient implements LlmClient {

  @Override
  public String generateText(String prompt) {
    if (prompt.contains("Output STRICT JSON only")) {
      return "{\n"
          + "  \"propertyDetails\": true,\n"
          + "  \"marketTrends\": true,\n"
          + "  \"demographics\": true,\n"
          + "  \"regulatoryRisk\": true,\n"
          + "  \"financialScenarios\": true,\n"
          + "  \"collateralValuation\": true,\n"
          + "  \"keyQuestions\": [\n"
          + "    \"What is current vacancy/absorption in the submarket?\",\n"
          + "    \"Are there floodplain or zoning constraints affecting value?\",\n"
          + "    \"How sensitive is DSCR to NOI decline and rate increases?\"\n"
          + "  ]\n"
          + "}\n";
    }

    return ""
        + "Executive Summary\n"
        + "- Rapid memo generated in mock mode. Replace with Vertex AI for narrative quality.\n"
        + "- Primary risks: leasing rollover, demand softness, rate sensitivity.\n"
        + "- Primary strengths: location, occupancy, sponsor equity.\n\n"
        + "Property Overview\n"
        + "- Address and property details per request and tool context.\n\n"
        + "Market & Demographics\n"
        + "- Market indicators and local demographics per tool context.\n\n"
        + "Regulatory / Environmental Risk\n"
        + "- Floodplain/zoning flags per tool context; verify with authoritative sources.\n\n"
        + "Financial Scenarios\n"
        + "- Base and stress DSCR calculations included; stress tests show covenant headroom.\n\n"
        + "Collateral Valuation\n"
        + "- Cap-rate proxy value range included; replace with appraisal/comps/DCF for production.\n\n"
        + "Recommendation\n"
        + "- Proceed to full underwriting with third-party reports; consider reserves and covenants.\n";
  }
}
