package org.hejki.spring.config;

import org.hejki.spring.config.internal.ExporterType;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.List;

/**
 * TODO Document me.
 *
 * @author Petr Hejkal
 */
public class ServiceExportBeanDefinitionParser extends AbstractRemoteBeanDefinitionParser {

    private static final String SERVICE_PROPERTY = "service";
    private static final String SERVICE_NAME_PROPERTY = "serviceName";
    private static final String PORT_ATTRIBUTE = "port";
    private static final String PORT_PROPERTY = "registryPort";

    @Override
    protected String getBeanClassNameByExporterType(ExporterType type) {
        return type.getExporterClassName();
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);

        List<Element> serviceBeanElement = DomUtils.getChildElements(element);
        Object service = parserContext.getDelegate().parsePropertySubElement(serviceBeanElement.get(0), builder.getBeanDefinition());

        builder.addPropertyValue(SERVICE_PROPERTY, service);

        if (ExporterType.RMI.name().equals(element.getAttribute(EXPORTER_ATTRIBUTE))) {
            builder.addPropertyValue(SERVICE_NAME_PROPERTY, element.getAttribute(NAME_ATTRIBUTE));

            String port = element.getAttribute(PORT_ATTRIBUTE);
            if (StringUtils.hasText(port)) {
                builder.addPropertyValue(PORT_PROPERTY, NumberUtils.parseNumber(port, Integer.class));
            }
        }
    }

    @Override
    protected boolean shouldGenerateId() {
        return true;
    }
}
