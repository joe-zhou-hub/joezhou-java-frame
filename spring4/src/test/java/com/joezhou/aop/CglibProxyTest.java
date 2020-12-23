package com.joezhou.aop;

import com.joezhou.aop.proxy.BearServiceImpl;
import com.joezhou.aop.proxy.CglibProxyCompany;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class CglibProxyTest {

    @Test
    public void cglibProxy() {
        BearServiceImpl customer = new BearServiceImpl();
        BearServiceImpl proxy = (BearServiceImpl) new CglibProxyCompany().hireProxy(customer);
        proxy.insert();
        proxy.select();
        proxy.update();
        proxy.delete();
    }
}
