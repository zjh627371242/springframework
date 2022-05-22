package demo.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description: jdk增强被代理方法功能
 * @author: zjh
 * @date: 2022年05月22日 12:36
 */
public class AlipayInvocationHandler implements InvocationHandler {
    private Object targetObject;
    public AlipayInvocationHandler(Object targetObject){
        this.targetObject = targetObject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforePay();
        Object result = method.invoke(targetObject, args);
        afterPay();
        return result;
    }
    private void beforePay(){
        System.out.println("从招行取款");
    }
    private void afterPay(){
        System.out.println("支付给慕课网");
    }
}
