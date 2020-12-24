package com.joezhou.aop;

import com.joezhou.aop.aspect.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JoeZhou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/aop/customer-annotation.xml")
public class CustomerTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void beforeAndAfterAdvice() {
        customerService.delete("zhaosi", 2);
    }
}
