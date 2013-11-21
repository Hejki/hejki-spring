package org.hejki.spring.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration("ServiceProxyBeanDefinitionParserTest.xml")
class ServiceProxyBeanDefinitionParserTest extends Specification {
    @Autowired
    ApplicationContext ctx

    @Unroll
    def "proxy bean with id #id"() {
        def bean

        when:
        bean = ctx.getBean(id)

        then:
        null != bean
        beanToString == bean.toString()

        where:
        id              | beanToString
        'hessian'       | 'HessianProxy[http://localhost:8080/moduleService]'
        'burlap'        | 'BurlapProxy[http://localhost:8080/moduleService]'
        'rmi'           | 'RMI invoker proxy for service URL [rmi://localhost:1199/moduleService]'
        'local'         | 'ModuleServiceImpl'
        'invoker'       | 'HTTP invoker proxy for service URL [http://localhost:8080/moduleService]'
    }
}
