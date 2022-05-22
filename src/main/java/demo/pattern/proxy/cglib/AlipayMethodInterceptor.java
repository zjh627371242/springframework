package demo.pattern.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: cglib增强被代理方法功能
 * @author: zjh
 * @date: 2022年05月22日 15:23
 */
public class AlipayMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        beforePay();
        Object result = methodProxy.invokeSuper(o, objects);
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
