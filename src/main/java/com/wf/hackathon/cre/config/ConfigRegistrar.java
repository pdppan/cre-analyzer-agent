package com.wf.hackathon.cre.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AppConfig.class, GcpConfig.class})
public class ConfigRegistrar {}
