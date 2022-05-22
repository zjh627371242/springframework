package demo.pattern.proxy;

import demo.pattern.proxy.cglib.AlipayMethodInterceptor;
import demo.pattern.proxy.cglib.CglibUtil;
import demo.pattern.proxy.impl.CommonPayment;
import demo.pattern.proxy.impl.ToBPaymentImpl;
import demo.pattern.proxy.impl.ToCPaymentImpl;
import demo.pattern.proxy.jdkproxy.AlipayInvocationHandler;
import demo.pattern.proxy.jdkproxy.JdkDynamicProxyUtil;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @Description: TODO
 * @author: zjh
 * @date: 2022年05月22日 12:31
 */
public class ProxyDemo {
    public static void main(String[] args) {
        ToCPayment toCPayment = new ToCPaymentImpl();
        AlipayInvocationHandler handler = new AlipayInvocationHandler(toCPayment);
        ToCPayment toCProxy = JdkDynamicProxyUtil.newProxyInstance(toCPayment, handler);
        toCProxy.pay();

        ToBPayment toBPayment = new ToBPaymentImpl();
        AlipayInvocationHandler handlerToB = new AlipayInvocationHandler(toBPayment);
        ToBPayment toBProxy = JdkDynamicProxyUtil.newProxyInstance(toBPayment, handlerToB);
        toBProxy.pay();

        CommonPayment commonPayment = new CommonPayment();
        MethodInterceptor methodInterceptor = new AlipayMethodInterceptor();
        CommonPayment proxy = CglibUtil.createProxy(commonPayment, methodInterceptor);
        proxy.pay();
    }
}
