package org.hejki.spring.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
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
public class SimpleHttpServerBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private static final String PORT = "port";

    @Override
    protected String getBeanClassName(Element element) {
        return "org.springframework.remoting.support.SimpleHttpServerFactoryBean";
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);

        String port = element.getAttribute(PORT);
        if (StringUtils.hasText(port)) {
            builder.addPropertyValue(PORT, NumberUtils.parseNumber(port, Integer.class));
        }

        List<Element> contexts = DomUtils.getChildElementsByTagName(element, "context");
        for (Element context : contexts) {

        }

    }

    //    SimpleHttpServerFactoryBean
}
