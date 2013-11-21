package org.hejki.spring.config.internal;


/**
 * TODO Document me.
 *
 * @author Petr Hejkal
 */
public enum ExporterType {
    LOCAL("org.hejki.spring.config.internal.LocalServiceExporter", "org.hejki.spring.config.internal.LocalProxyFactoryBean"),
    RMI("org.springframework.remoting.rmi.RmiServiceExporter", "org.springframework.remoting.rmi.RmiProxyFactoryBean"),
    HESSIAN("org.springframework.remoting.caucho.HessianServiceExporter", "org.springframework.remoting.caucho.HessianProxyFactoryBean"),
    BURLAP("org.springframework.remoting.caucho.BurlapServiceExporter", "org.springframework.remoting.caucho.BurlapProxyFactoryBean"),
    HTTP_INVOKER("org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter", "org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean"),
    ;

    private final String exporterClassName;
    private final String proxyFactoryClassName;

    ExporterType(String exporterClassName, String proxyFactoryClassName) {
        this.exporterClassName = exporterClassName;
        this.proxyFactoryClassName = proxyFactoryClassName;
    }

    public String getExporterClassName() {
        return exporterClassName;
    }

    public String getProxyFactoryClassName() {
        return proxyFactoryClassName;
    }
}
