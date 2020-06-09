package concern;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class Logger {

    @AfterThrowing(pointcut = "execution(public * service.customer.CustomerService.findAll(..))")
    public void error() {
        System.out.println("[CMS] ERROR!");
    }
}
