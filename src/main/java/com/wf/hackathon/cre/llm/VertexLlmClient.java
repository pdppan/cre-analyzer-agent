package com.wf.hackathon.cre.llm;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.Part;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import com.wf.hackathon.cre.config.GcpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Minimal Vertex AI (Gemini) wrapper using Application Default Credentials (ADC). */
public class VertexLlmClient implements LlmClient {
    private static final Logger log = LoggerFactory.getLogger(VertexLlmClient.class);
    private final GcpConfig gcp;

    public VertexLlmClient(GcpConfig gcp) {
        this.gcp = gcp;
    }

    @Override
    public String generateText(String prompt) {
        if (gcp.projectId() == null || gcp.projectId().isBlank()) {
            throw new IllegalStateException("GCP projectId missing. Set GCP_PROJECT_ID or gcp.projectId.");
        }

        try (VertexAI vertexAI = new VertexAI(gcp.projectId(), gcp.location())) {
            GenerativeModel model = new GenerativeModel(gcp.vertexModel(), vertexAI);

            Content content = Content.newBuilder()
                    .setRole("user")
                    .addParts(Part.newBuilder().setText(prompt).build())
                    .build();

            GenerateContentResponse response = model.generateContent(content);
            return ResponseHandler.getText(response);
        } catch (Exception e) {
            log.error("Vertex AI call failed", e);
            throw new RuntimeException("Vertex AI call failed: " + e.getMessage(), e);
        }
    }
}
