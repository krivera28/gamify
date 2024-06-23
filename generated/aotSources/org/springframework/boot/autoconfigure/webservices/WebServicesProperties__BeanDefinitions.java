package org.springframework.boot.autoconfigure.webservices;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link WebServicesProperties}.
 */
@Generated
public class WebServicesProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'webServicesProperties'.
   */
  public static BeanDefinition getWebServicesPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebServicesProperties.class);
    beanDefinition.setInstanceSupplier(WebServicesProperties::new);
    return beanDefinition;
  }
}
