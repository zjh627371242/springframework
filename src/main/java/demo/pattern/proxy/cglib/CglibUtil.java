package demo.pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @Description: TODO
 * @author: zjh
 * @date: 2022年05月22日 15:25
 */
public class CglibUtil {
    /**
     * 创建代理对象
     * @param targetObject 被代理对象
     * @param methodInterceptor 代理功能
     * @param <T> 代理类类型
     * @return 代理类实例
     */
    public static <T> T createProxy(T targetObject, MethodInterceptor methodInterceptor){
        return (T) Enhancer.create(targetObject.getClass(),methodInterceptor);
    }
}
