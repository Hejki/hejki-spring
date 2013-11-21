package org.hejki.spring.config;

import org.hejki.spring.config.internal.ExporterType;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * TODO Document me.
 *
 * @author Petr Hejkal
 */
public class ServiceProxyBeanDefinitionParser extends AbstractRemoteBeanDefinitionParser {

    private static final String SERVICE_URL_ATTRIBUTE = "service-url";
    private static final String SERVICE_URL_PROPERTY = "serviceUrl";

    @Override
    protected String getBeanClassNameByExporterType(ExporterType type) {
        return type.getProxyFactoryClassName();
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);

        builder.addPropertyValue(SERVICE_URL_PROPERTY, element.getAttribute(SERVICE_URL_ATTRIBUTE));
    }
}
