package org.simpleframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @Description: TODO
 * @author: zjh
 * @date: 2022年05月22日 17:37
 */
public class ProxyCreator {

    /**
     * 创建动态代理对象并返回
     * @param targetClass 被代理的class对象
     * @param methodInterceptor 方法拦截器
     * @return
     */
    public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor){
        return Enhancer.create(targetClass,methodInterceptor);
    }
}
