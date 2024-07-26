package com.mdtech.template.controller;

import com.lowagie.text.DocumentException;
import com.mdtech.template.service.TemplateService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/template/html")
    public String getTemplate(@RequestParam String templateName){
        return templateService.generateHtmlTemplate(templateName);
    }

    @GetMapping("/template/html/{templateName}/pdf")
    @ResponseBody
    public void generatePdf(@PathVariable("templateName") String templateName, HttpServletResponse response)
            throws IOException, DocumentException {
        String template = templateService.generateHtmlTemplate(templateName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(template);
        renderer.layout();
        renderer.createPDF(outputStream, false);
        renderer.finishPDF();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.pdf", templateName));
        response.getOutputStream().write(outputStream.toByteArray());
        response.getOutputStream().flush();
    }
}
