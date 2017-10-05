package jdbc.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringConfigUtil implements ApplicationContextAware {

    private static ApplicationContext ac = null;
    private static SpringConfigUtil springConfigTool = null;

    public synchronized static SpringConfigUtil init() {
        if (springConfigTool == null) {
            springConfigTool = new SpringConfigUtil();
        }
        return springConfigTool;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    public synchronized static Object getBean(String beanName) {
        return ac.getBean(beanName);
    }
}