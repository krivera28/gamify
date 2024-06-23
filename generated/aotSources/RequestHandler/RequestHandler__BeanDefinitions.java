package RequestHandler;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RequestHandler}.
 */
@Generated
public class RequestHandler__BeanDefinitions {
  /**
   * Get the bean definition for 'requestHandler'.
   */
  public static BeanDefinition getRequestHandlerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RequestHandler.class);
    beanDefinition.setInstanceSupplier(RequestHandler::new);
    return beanDefinition;
  }
}
