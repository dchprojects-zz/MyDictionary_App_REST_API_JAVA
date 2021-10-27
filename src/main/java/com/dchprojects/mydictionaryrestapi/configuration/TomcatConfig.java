package com.dchprojects.mydictionaryrestapi.configuration;

import org.apache.catalina.Context;
import org.apache.catalina.webresources.ExtractingRoot;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    TomcatServletWebServerFactory tomcatFactory() {
        return new TomcatServletWebServerFactory() {

            @Override
            protected void postProcessContext(Context context) {
                context.setResources(new ExtractingRoot());
            }
        };
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainerCustomizer() {
        return container -> container.addContextCustomizers(
                cntxt -> cntxt.setReloadable(false));
    }

}