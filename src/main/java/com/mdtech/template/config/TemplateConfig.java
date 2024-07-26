package com.mdtech.template.config;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

public class TemplateConfig {

    @Bean
    public TemplateEngine htmlTemplateEngine() {
        return templateEngine(TemplateMode.HTML);
    }

    @Bean
    public TemplateEngine textTemplateEngine() {
        return templateEngine(TemplateMode.TEXT);
    }

    private TemplateEngine templateEngine(TemplateMode templateMode) {
        TemplateEngine engine = new TemplateEngine();
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(templateMode);
        engine.setTemplateResolver(templateResolver);
        return engine;
    }
}
