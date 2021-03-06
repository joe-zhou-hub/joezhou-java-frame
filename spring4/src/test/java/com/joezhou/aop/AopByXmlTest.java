package com.joezhou.aop;

import com.joezhou.aop.aspect.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * @author JoeZhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/aop/customer-xml.xml")
public class AopByXmlTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void beforeAndAfterAdvice() {
        customerService.delete("zhaosi", 2);
    }

    @Test
    public void afterReturningAdvice() {
        System.out.println("test: " + customerService.select("zhaosi"));
    }

    @Test
    public void afterThrowingAdvice() {
        customerService.update(null);
    }

    @Test
    public void aroundAdvice() {
        System.out.println(customerService.insert(new HashMap<>()));
        System.out.println("------");
        System.out.println(customerService.insert(null));
    }
}
