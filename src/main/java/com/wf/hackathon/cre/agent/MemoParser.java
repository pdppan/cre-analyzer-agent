package com.wf.hackathon.cre.agent;

import com.wf.hackathon.cre.model.DealMemo;

import java.util.*;

public final class MemoParser {
  private MemoParser() {}

  public static DealMemo parseToSections(String text) {
    Map<String, String> s = splitByHeadings(text);
    return new DealMemo(
        s.getOrDefault("Executive Summary", ""),
        s.getOrDefault("Property Overview", ""),
        s.getOrDefault("Market & Demographics", ""),
        s.getOrDefault("Regulatory / Environmental Risk", ""),
        s.getOrDefault("Financial Scenarios", ""),
        s.getOrDefault("Collateral Valuation", ""),
        s.getOrDefault("Recommendation", "")
    );
  }

  private static Map<String, String> splitByHeadings(String raw) {
    String[] headings = {
        "Executive Summary",
        "Property Overview",
        "Market & Demographics",
        "Regulatory / Environmental Risk",
        "Financial Scenarios",
        "Collateral Valuation",
        "Recommendation"
    };

    Map<String, Integer> pos = new LinkedHashMap<>();
    for (String h : headings) {
      int idx = indexOfHeading(raw, h);
      if (idx >= 0) pos.put(h, idx);
    }

    Map<String, String> out = new LinkedHashMap<>();
    if (pos.isEmpty()) {
      out.put("Executive Summary", raw.strip());
      return out;
    }

    //var entries = pos.entrySet().stream().sorted(Map.Entry.comparingByValue).toList();
      var entries = pos.entrySet().stream()
              .sorted(Map.Entry.comparingByValue())
              .toList();

      for (int i = 0; i < entries.size(); i++) {
      String h = entries.get(i).getKey();
      int start = entries.get(i).getValue();
      int end = (i + 1 < entries.size()) ? entries.get(i + 1).getValue() : raw.length();

      String block = raw.substring(start, end).strip();
      String cleaned = block.replaceFirst("(?is)^\s*(?:#+\s*)?" + java.util.regex.Pattern.quote(h) + "\s*", "").strip();
      out.put(h, cleaned);
    }
    return out;
  }

  private static int indexOfHeading(String text, String heading) {
    var pattern = java.util.regex.Pattern.compile("(?im)^\s*(?:#+\s*)?" + java.util.regex.Pattern.quote(heading) + "\s*$");
    var m = pattern.matcher(text);
    return m.find() ? m.start() : -1;
  }
}
