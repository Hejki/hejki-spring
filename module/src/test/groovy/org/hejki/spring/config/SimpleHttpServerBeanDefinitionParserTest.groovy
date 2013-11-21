package org.hejki.spring.config

import com.sun.net.httpserver.HttpServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration("SimpleHttpServerBeanDefinitionParserTest.xml")
class SimpleHttpServerBeanDefinitionParserTest extends Specification {
    @Autowired
    ApplicationContext ctx

    def "simple server"() {
        HttpServer server

        when:
        server = ctx.getBean('simpleServer')

        then:
        9090 == server.address.port
        null != server.server.contexts.findContext('http', '/service')
    }
}
