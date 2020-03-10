package aop;

import aop.T.MathCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOP_test {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator bean = applicationContext.getBean(MathCalculator.class);
        bean.div(1, 1);
        applicationContext.close();
    }
}
