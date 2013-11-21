package org.hejki.spring.config;

import org.hejki.spring.config.internal.ExporterType;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;

/**
 * TODO Document me.
 *
 * @author Petr Hejkal
 */
public abstract class AbstractRemoteBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    protected static final String EXPORTER_ATTRIBUTE = "exporter";
    private static final String SERVICE_INTERFACE_ATTRIBUTE = "service-interface";
    private static final String SERVICE_INTERFACE_PROPERTY = "serviceInterface";

    @Override
    protected String getBeanClassName(Element element) {
        String exporterAttr = element.getAttribute(EXPORTER_ATTRIBUTE);

        exporterAttr = exporterAttr.toUpperCase().replace('-', '_');
        return getBeanClassNameByExporterType(ExporterType.valueOf(exporterAttr));
    }

    protected abstract String getBeanClassNameByExporterType(ExporterType type);

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);

        String serviceInterfaceClassName = element.getAttribute(SERVICE_INTERFACE_ATTRIBUTE);

        try {
            Class serviceInterface = ClassUtils.forName(serviceInterfaceClassName, ClassUtils.getDefaultClassLoader());
            builder.addPropertyValue(SERVICE_INTERFACE_PROPERTY, serviceInterface);
        } catch (ClassNotFoundException e) {
            parserContext.getReaderContext()
                         .error("Cannot find service interface class [" + serviceInterfaceClassName + "].", element, e);
        }
    }
}
