package demo.pattern.proxy.impl;

import demo.pattern.proxy.ToBPayment;

/**
 * @Description: TODO
 * @author: zjh
 * @date: 2022年05月22日 12:32
 */
public class ToBPaymentImpl implements ToBPayment {
    @Override
    public void pay() {
        System.out.println("以公司的名义进行支付");
    }
}
