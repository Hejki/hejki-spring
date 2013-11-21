package org.hejki.spring.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * {@link org.springframework.beans.factory.xml.NamespaceHandler}
 * for the '{@code module}' namespace.
 *
 * @author Petr Hejkal
 */
public class ModuleNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("service-export", new ServiceExportBeanDefinitionParser());
        registerBeanDefinitionParser("service-proxy", new ServiceProxyBeanDefinitionParser());
        registerBeanDefinitionParser("simple-http-server", new SimpleHttpServerBeanDefinitionParser());
    }
}
