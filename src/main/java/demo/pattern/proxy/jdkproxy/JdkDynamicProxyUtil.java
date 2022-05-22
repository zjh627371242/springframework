package demo.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description: TODO
 * @author: zjh
 * @date: 2022年05月22日 12:39
 */
public class JdkDynamicProxyUtil {
    /**
     * 创建代理对象
     * @param targetObject 被代理对象
     * @param handler 代理功能
     * @param <T> 代理类类型
     * @return 代理类实例
     */
    public static <T> T newProxyInstance(T targetObject, InvocationHandler handler){
        ClassLoader classLoader = targetObject.getClass().getClassLoader();
        Class<?>[] interfaces = targetObject.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader,interfaces,handler);
    }
}
