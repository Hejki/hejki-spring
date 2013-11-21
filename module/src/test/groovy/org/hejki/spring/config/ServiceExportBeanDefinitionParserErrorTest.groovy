package org.hejki.spring.config

import org.springframework.beans.factory.parsing.BeanDefinitionParsingException
import org.springframework.beans.factory.xml.XmlBeanDefinitionStoreException
import org.springframework.context.support.ClassPathXmlApplicationContext
import spock.lang.Specification
import spock.lang.Unroll

class ServiceExportBeanDefinitionParserErrorTest extends Specification {

    @Unroll
    def "export bean fail with bad application context '#ctxPath'"() {
        when:
        new ClassPathXmlApplicationContext("org/hejki/spring/config/${ctxPath}")

        then:
        thrown(ex)

        where:
        ctxPath                                     | ex
        'ServiceExportBeanDefinitionParserErrorTest_1.xml' | XmlBeanDefinitionStoreException
        'ServiceExportBeanDefinitionParserErrorTest_2.xml' | XmlBeanDefinitionStoreException
        'ServiceExportBeanDefinitionParserErrorTest_3.xml' | XmlBeanDefinitionStoreException
        'ServiceExportBeanDefinitionParserErrorTest_4.xml' | XmlBeanDefinitionStoreException
        'ServiceExportBeanDefinitionParserErrorTest_5.xml' | XmlBeanDefinitionStoreException
        'ServiceExportBeanDefinitionParserErrorTest_6.xml' | BeanDefinitionParsingException
    }
}
