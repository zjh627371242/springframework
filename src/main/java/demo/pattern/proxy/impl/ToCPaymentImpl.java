package demo.pattern.proxy.impl;

import demo.pattern.proxy.ToCPayment;

/**
 * @Description: TODO
 * @author: zjh
 * @date: 2022年05月22日 12:33
 */
public class ToCPaymentImpl implements ToCPayment {
    @Override
    public void pay() {
        System.out.println("以用户的名义进行支付");
    }
}
