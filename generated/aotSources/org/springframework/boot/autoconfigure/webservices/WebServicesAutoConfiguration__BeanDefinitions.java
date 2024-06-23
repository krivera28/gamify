package org.springframework.boot.autoconfigure.webservices;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

/**
 * Bean definitions for {@link WebServicesAutoConfiguration}.
 */
@Generated
public class WebServicesAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'webServicesAutoConfiguration'.
   */
  public static BeanDefinition getWebServicesAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebServicesAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(WebServicesAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'messageDispatcherServlet'.
   */
  private static BeanInstanceSupplier<ServletRegistrationBean> getMessageDispatcherServletInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ServletRegistrationBean>forFactoryMethod(WebServicesAutoConfiguration.class, "messageDispatcherServlet", ApplicationContext.class, WebServicesProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebServicesAutoConfiguration.class).messageDispatcherServlet(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'messageDispatcherServlet'.
   */
  public static BeanDefinition getMessageDispatcherServletBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ServletRegistrationBean.class);
    beanDefinition.setTargetType(ResolvableType.forClassWithGenerics(ServletRegistrationBean.class, MessageDispatcherServlet.class));
    beanDefinition.setInstanceSupplier(getMessageDispatcherServletInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link WebServicesAutoConfiguration.WsConfiguration}.
   */
  @Generated
  public static class WsConfiguration {
    /**
     * Get the bean definition for 'wsConfiguration'.
     */
    public static BeanDefinition getWsConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(WebServicesAutoConfiguration.WsConfiguration.class);
      beanDefinition.setInstanceSupplier(WebServicesAutoConfiguration.WsConfiguration::new);
      return beanDefinition;
    }
  }
}
