package com.wf.hackathon.cre.tools;

import com.wf.hackathon.cre.model.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CollateralValuationTool {

  public Map<String, Object> estimate(DealRequest req) {
    Property p = req.property();
    Assumptions a = req.assumptions();

    double noi = p.noiAnnual();
    double exitCap = a.exitCapRatePct();

    double impliedValue = exitCap == 0 ? 0 : noi / exitCap;
    double ltv = impliedValue == 0 ? 0 : req.loan().requestedLoanAmount() / impliedValue;

    double capTight = Math.max(0.0001, exitCap - 0.005);
    double capWide  = exitCap + 0.005;

    double valueTight = noi / capTight;
    double valueWide  = noi / capWide;

    return Map.of(
        "exitCapRatePct", exitCap,
        "impliedValue", impliedValue,
        "valueRange", Map.of("capMinus50bps", valueTight, "capPlus50bps", valueWide),
        "ltv", ltv,
        "notes", "Cap-rate proxy. Replace with appraisal/comps/DCF for production."
    );
  }
}
