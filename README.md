# Commercial Real Estate Analyzer Agent (GCP + Java Spring Boot)

This is a hackathon-ready **AI agent** that automates Commercial Real Estate (CRE) deal analysis:
- Java 17 + Spring Boot 3
- GCP Vertex AI (Gemini) for planning + memo generation
- Clean “tool/provider” interfaces for public + market data (mock implementations included)

## Run (mock mode, no external calls)
```bash
mvn spring-boot:run
```

```bash
curl -X POST http://localhost:8080/api/cre/analyze \
  -H "Content-Type: application/json" \
  -d @samples/deal-request.json
```

## Use Vertex AI (Gemini)
1) Authenticate (ADC):
```bash
gcloud auth application-default login
```

2) Set environment variables:
- `GCP_PROJECT_ID`
- `GCP_LOCATION` (e.g., `us-central1`)
- `VERTEX_MODEL` (e.g., `gemini-1.5-flash`)

3) Run with mock mode disabled:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--app.mockMode=false"
```

## Architecture (summary)
1) `CreAnalyzerAgent` asks the LLM for a minimal **plan** (what to fetch).
2) Executes tool calls:
   - Property details
   - Market trends
   - Demographics
   - Regulatory/environmental risk
   - Financial scenarios (DSCR stress)
   - Collateral valuation (cap-rate proxy)
3) Sends consolidated context back to the LLM to generate a **bank-style deal memo**.

## REST API
`POST /api/cre/analyze`

Returns JSON with memo sections:
- Executive Summary
- Property Overview
- Market & Demographics
- Regulatory / Environmental Risk
- Financial Scenarios
- Collateral Valuation
- Recommendation

## Data providers
Provider interfaces are in `src/main/java/.../data`.
Mock providers are wired automatically when `app.mockMode=true`.
HTTP provider skeletons are included as placeholders for wiring:
- Census ACS, BLS, FRED, FEMA flood, local zoning/permits, etc.

License: MIT
