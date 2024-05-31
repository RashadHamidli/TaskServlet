package com.company.config;


import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.Getter;

public class FreeMarkerConfig {
    @Getter
    private static final Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

    static {
        configuration.setClassForTemplateLoading(FreeMarkerConfig.class, "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setFallbackOnNullLoopVariable(false);
    }

}


