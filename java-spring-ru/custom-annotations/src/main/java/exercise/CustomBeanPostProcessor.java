package exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.StringJoiner;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

    @Override // Выполняется перед инициализацией каждого бина.
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Inspect.class)) {
            Inspect inspectAnnotation = beanClass.getAnnotation(Inspect.class);
            String logLevel = inspectAnnotation.level();
        }
        return bean;
        //return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override // Выполняется после инициализации каждого бина.
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Inspect.class)) {
            Inspect inspectAnnotation = beanClass.getAnnotation(Inspect.class);
            String logLevel = inspectAnnotation.level();

            return Proxy.newProxyInstance(
                beanClass.getClassLoader(),
                beanClass.getInterfaces(),
                // Лямбда - обработчик вызова.
                // В качестве аргумента принимает сам объект прокси, метод, к которому происходит обращение
                // и массив аргументов, переданных при вызове.
                (proxy, method, args) -> {
                    String methodName = method.getName();
                    StringJoiner sj = new StringJoiner(", ");
                    Arrays.stream(args).map(String::valueOf).forEach(sj::add);
                    String message = String.format("Was called method: %s() with arguments: [%s]", methodName, sj);
                    if (logLevel.equals("info")) {
                        LOGGER.info(message);
                    } else if (logLevel.equals("debug")) {
                        LOGGER.debug(message);
                    }
                    return method.invoke(bean, args);
                }
            );
        }
        return bean;
        //return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
// END
// Proxy – это особый объект,
// с помощью которого можно управлять доступом к свойствам практически любых объектов.
// Прокси может менять поведение исходного объекта.
// Proxy оборачивает исходный объект и перехватывает запросы к нему.
// Прикладной код не должен знать, что он работает не с исходным объектом, а с Proxy.
// Метод newProxyInstance() создаёт новый экземпляр прокси.
// На вход принимает getClassLoader для класса, массив реализуемых интерфейсов и обработчик вызова
