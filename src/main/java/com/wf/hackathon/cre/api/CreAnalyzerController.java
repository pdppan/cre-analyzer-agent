package com.wf.hackathon.cre.api;

import com.wf.hackathon.cre.agent.CreAnalyzerAgent;
import com.wf.hackathon.cre.model.DealMemoResponse;
import com.wf.hackathon.cre.model.DealRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cre")
public class CreAnalyzerController {

  private final CreAnalyzerAgent agent;

  public CreAnalyzerController(CreAnalyzerAgent agent) {
    this.agent = agent;
  }

  @PostMapping(value = "/analyze", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public DealMemoResponse analyze(@Valid @RequestBody DealRequest request) {
    return agent.analyze(request);
  }
}
