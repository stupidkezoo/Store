package org.kezoo.store;

import org.kezoo.store.service.ConsoleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    @Autowired
    private ConsoleListener consoleListener;

    private Application() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AutowireCapableBeanFactory acbFactory = context.getAutowireCapableBeanFactory();
        acbFactory.autowireBean(this);
    }
    public static void main(String[] args) {
        Application application = new Application();
        application.consoleListener.start();
    }
}
