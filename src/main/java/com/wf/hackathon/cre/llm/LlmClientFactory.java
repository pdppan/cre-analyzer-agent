package com.wf.hackathon.cre.llm;

import com.wf.hackathon.cre.config.AppConfig;
import com.wf.hackathon.cre.config.GcpConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LlmClientFactory {

    @Bean
    public LlmClient llmClient(AppConfig appConfig, GcpConfig gcpConfig) {
        if (appConfig.mockMode()) return new MockLlmClient();
        return new VertexLlmClient(gcpConfig);
    }

}
