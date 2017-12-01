package com.kcview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kcview.entity.SuiviComptant;
import com.kcview.vo.BilanRelanceHebdo;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String message, List<SuiviComptant> suivis) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("suivis", suivis);
        return templateEngine.process("mailTemplate", context);
    }
    
    public String buildBilanRelanceHebdo(String message, List<BilanRelanceHebdo> bilansComptant, List<BilanRelanceHebdo> bilansPea, List<BilanRelanceHebdo> bilansRib) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("bilansComptant", bilansComptant);
        context.setVariable("bilansPea", bilansPea);
        context.setVariable("bilansRib", bilansRib);
        return templateEngine.process("mailBilanRelanceHebdoTemplate", context);
    }

}