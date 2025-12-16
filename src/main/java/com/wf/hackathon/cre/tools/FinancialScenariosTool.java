package com.wf.hackathon.cre.tools;

import com.wf.hackathon.cre.model.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FinancialScenariosTool {

  public Map<String, Object> run(DealRequest req) {
    Property p = req.property();
    Loan l = req.loan();
    Assumptions a = req.assumptions();

    double noi = p.noiAnnual();
    double loanAmt = l.requestedLoanAmount();
    double rate = l.interestRatePct();

    // Simplified annual debt service approximation (interest-only) for demo purposes.
    double annualDebtService = loanAmt * rate;

    double baseDscr = annualDebtService == 0 ? 0 : noi / annualDebtService;

    double stressNoi = noi * (1.0 - a.stressNoiDownPct());
    double stressDscr = annualDebtService == 0 ? 0 : stressNoi / annualDebtService;

    Map<String, Object> out = new HashMap<>();
    out.put("annualDebtServiceApprox", annualDebtService);
    out.put("base", Map.of("noi", noi, "dscr", baseDscr));
    out.put("stress", Map.of(
        "noi", stressNoi,
        "dscr", stressDscr,
        "stressVacancyAddlPct", a.stressVacancyAddlPct(),
        "stressNoiDownPct", a.stressNoiDownPct()
    ));
    out.put("notes", "Debt service is simplified. Replace with amortizing schedule for production.");
    return out;
  }
}
