package org.springframework.boot.autoconfigure.webservices.client;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;

/**
 * Bean definitions for {@link WebServiceTemplateAutoConfiguration}.
 */
@Generated
public class WebServiceTemplateAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'webServiceTemplateAutoConfiguration'.
   */
  public static BeanDefinition getWebServiceTemplateAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebServiceTemplateAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(WebServiceTemplateAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'webServiceTemplateBuilder'.
   */
  private static BeanInstanceSupplier<WebServiceTemplateBuilder> getWebServiceTemplateBuilderInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<WebServiceTemplateBuilder>forFactoryMethod(WebServiceTemplateAutoConfiguration.class, "webServiceTemplateBuilder", ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebServiceTemplateAutoConfiguration.class).webServiceTemplateBuilder(args.get(0)));
  }

  /**
   * Get the bean definition for 'webServiceTemplateBuilder'.
   */
  public static BeanDefinition getWebServiceTemplateBuilderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebServiceTemplateBuilder.class);
    beanDefinition.setInstanceSupplier(getWebServiceTemplateBuilderInstanceSupplier());
    return beanDefinition;
  }
}
