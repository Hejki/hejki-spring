package org.hejki.spring.config.internal;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.remoting.support.UrlBasedRemoteAccessor;

/**
 * TODO Document me.
 *
 * @author Petr Hejkal
 */
public class LocalProxyFactoryBean extends UrlBasedRemoteAccessor implements ApplicationContextAware, FactoryBean<Object> {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object getObject() throws Exception {
        return applicationContext.getBean(getServiceUrl());
    }

    @Override
    public Class<?> getObjectType() {
        return getServiceInterface();
    }

    @Override
    public boolean isSingleton() {
        return applicationContext.isSingleton(getServiceUrl());
    }
}
