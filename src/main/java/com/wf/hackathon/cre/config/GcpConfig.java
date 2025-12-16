package com.wf.hackathon.cre.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gcp")
public record GcpConfig(String projectId, String location, String vertexModel) {}
