package com.mdtech.template.service;

import com.mdtech.template.model.Person;
import com.mdtech.template.model.Skill;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;

@Service
public class TemplateService {

    private final TemplateEngine textTemplateEngine;

    private final TemplateEngine htmlTemplateEngine;

    public TemplateService(TemplateEngine textTemplateEngine, TemplateEngine htmlTemplateEngine) {
        this.textTemplateEngine = textTemplateEngine;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    public String generateHtmlTemplate(String templateName) {
        Context context = new Context();
        context.setVariables(templateVariables());
        String template = htmlTemplateEngine.process(templateName, context);
        return template;
    }

    public String generateTextTemplate(String templateName) {
        return null;
    }

    private Map<String, Object> templateVariables() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("person", new Person("Test user", 30));
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Java", "Expert"));
        skills.add(new Skill("Python", "Intermediate"));
        variables.put("skills", skills);
        return variables;
    }
}
