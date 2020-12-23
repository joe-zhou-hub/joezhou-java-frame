package com.joezhou.aop;

import com.joezhou.aop.proxy.JdkProxyCompany;
import com.joezhou.aop.proxy.UserService;
import com.joezhou.aop.proxy.UserServiceImpl;
import org.junit.Test;

/**
 * @author JoeZhou
 */
public class JdkProxyTest {

    @Test
    public void jdkProxy() {

        // 创建客户：这里使用实现类类型接收也一样
        UserService zhaosi = new UserServiceImpl();

        // 为zhaosi聘用代理，需要把zhaosi传入方法
        // 代理必须被强转成客户类型，即代理的类型要和客户类型一致
        UserService liuneng = (UserService) new JdkProxyCompany().hireProxy(zhaosi);

        // 让代理干活：代理开始执行工作清单invoke()中的代码
        liuneng.insert();
        liuneng.select();
        liuneng.update();
        liuneng.delete();
    }
}
