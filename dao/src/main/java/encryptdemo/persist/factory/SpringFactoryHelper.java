package encryptdemo.persist.factory;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by esmiley on 7/29/15.
 */
public class SpringFactoryHelper {
    private SpringFactoryHelper(){}
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-context.xml");

    public static Key getKey(){
        return (Key) applicationContext.getBean("key");
    }
}
