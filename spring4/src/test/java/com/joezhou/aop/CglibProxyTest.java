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

        // 创建客户
        BearServiceImpl xiaoda = new BearServiceImpl();


        // 从公司中为xiaoda聘用一个代理，并把xiaoda传给方法
        // 注意：代理必须被强转成BearServiceImpl类型，否则调用不了业务方法
        BearServiceImpl xionger = (BearServiceImpl) new CglibProxyCompany().hireProxy(xiaoda);

        // 让代理干活：代理去执行工作清单invoke()
        xionger.insert();
        xionger.select();
        xionger.update();
        xionger.delete();
    }
}
