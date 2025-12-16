package com.wf.hackathon.cre.llm;

public final class PromptTemplates {
  private PromptTemplates() {}

  public static String plannerPrompt(String dealJson) {
    return "You are a CRE underwriting analyst agent. Create a minimal JSON plan of what to fetch.\n"
        + "Output STRICT JSON only with keys:\n"
        + "  propertyDetails: boolean,\n"
        + "  marketTrends: boolean,\n"
        + "  demographics: boolean,\n"
        + "  regulatoryRisk: boolean,\n"
        + "  financialScenarios: boolean,\n"
        + "  collateralValuation: boolean,\n"
        + "  keyQuestions: string[]\n"
        + "Context (deal request JSON):\n"
        + dealJson;
  }

  public static String memoPrompt(String dealJson, String toolContextJson) {
    return "You are a senior commercial real estate credit officer producing a bank-grade DEAL MEMO.\n"
        + "Use the provided deal request and tool context. Be concise but complete.\n\n"
        + "REQUIREMENTS:\n"
        + "- Use headings exactly:\n"
        + "  Executive Summary\n"
        + "  Property Overview\n"
        + "  Market & Demographics\n"
        + "  Regulatory / Environmental Risk\n"
        + "  Financial Scenarios\n"
        + "  Collateral Valuation\n"
        + "  Recommendation\n"
        + "- Provide bullet points where appropriate.\n"
        + "- Call out DSCR, LTV, key sensitivities and red flags.\n"
        + "- If data is missing, state assumptions explicitly.\n\n"
        + "Deal Request (JSON):\n" + dealJson + "\n\n"
        + "Tool Context (JSON):\n" + toolContextJson;
  }
}
