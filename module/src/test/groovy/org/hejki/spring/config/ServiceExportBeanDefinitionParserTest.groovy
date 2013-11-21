package org.hejki.spring.config
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.remoting.caucho.BurlapServiceExporter
import org.springframework.remoting.caucho.HessianServiceExporter
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter
import org.springframework.remoting.rmi.RmiServiceExporter
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration("ServiceExportBeanDefinitionParserTest.xml")
class ServiceExportBeanDefinitionParserTest extends Specification {
    @Autowired
    ApplicationContext ctx

    @Unroll
    def "export bean with name #name"() {
        def bean

        when:
        bean = ctx.getBean(name)

        then:
        exporterClass == bean.class
        'org.hejki.spring.config.ModuleServiceImpl' == bean.service.class.name
        'org.hejki.spring.config.ModuleService' == bean.serviceInterface.name

        where:
        name           | exporterClass
        '/hessian'     | HessianServiceExporter
        '/burlap'      | BurlapServiceExporter
        '/httpService' | HttpInvokerServiceExporter
    }

    def "export RMI bean"() {
        RmiServiceExporter bean

        when:
        bean = ctx.getBean('rmiService')

        then:
        RmiServiceExporter == bean.class
        'org.hejki.spring.config.ModuleServiceImpl' == bean.service.class.name
        'org.hejki.spring.config.ModuleService' == bean.serviceInterface.name
        1199 == bean.registryPort
    }

    def "export LOCAL bean"() {
        def bean

        when:
        bean = ctx.getBean('localService')

        then:
        ModuleServiceImpl == bean.class
        bean == ctx.getBean('moduleService')
    }
}

interface ModuleService {
    void doService()
}

class ModuleServiceImpl implements ModuleService {
    @Override
    void doService() {
    }

    @Override
    String toString() {
        return "ModuleServiceImpl"
    }
}
