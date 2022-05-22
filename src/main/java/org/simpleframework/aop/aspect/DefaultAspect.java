package org.simpleframework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Description: 定义支持的advice
 * @author: zjh
 * @date: 2022年05月22日 16:41
 */
public abstract class DefaultAspect {
    /**
     * 事前拦截器
     * @param targetClass 被代理的目标类
     * @param method 被代理的目标方法
     * @param args 被代理的目标方法对应的参数列表
     * @throws Throwable
     */
    public void before(Class<?> targetClass, Method method,Object[] args){

    }
    /**
     * 事后拦截器
     * @param targetClass 被代理的目标类
     * @param method 被代理的目标方法
     * @param args 被代理的目标方法对应的参数列表
     * @param returnValue 被代理目标方法执行后的返回值
     * @throws Throwable
     */
    public Object afterReturning(Class<?> targetClass, Method method,Object[] args,Object returnValue){
        return returnValue;
    }

    /**
     * 被代理方法执行异常后执行
     * @param targetClass 被代理的目标类
     * @param method 被代理的目标方法
     * @param args 被代理的目标方法对应的参数列表
     * @param e 被代理的目标方法抛出的异常
     * @throws Throwable
     */
    public void afterThrowing(Class<?> targetClass, Method method,Object[] args,Throwable e){

    }
}
