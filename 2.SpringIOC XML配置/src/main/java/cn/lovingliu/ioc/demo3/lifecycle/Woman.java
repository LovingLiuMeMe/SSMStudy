package cn.lovingliu.ioc.demo3.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Woman implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String name;

    public Woman(){
        System.out.println("第一步:被实例化");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("第二步:populate properties 封装属性");
        this.name = name;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("第三步:设置Bean的名称"+name +" (其中name的就是配置文件中的id或name)");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("第四步:了解工厂的信息"+applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第六步:属性设置完成调用");
    }

    public void initMethod(){
        System.out.println("第七步:执行<bean init-method=\"initMethod\"/> 中的方法");
    }
    public void run(){
        System.out.println("第九步:执行该类自定义的方法（自己的业务逻辑代码在此执行）");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("第十步:执行Spring的销毁方法");
    }

    public void destoryMethod(){
        System.out.println("第十一步:<bean destory-method=\"destoryMethod\"/>执行自定义的销毁方法");
    }
}
