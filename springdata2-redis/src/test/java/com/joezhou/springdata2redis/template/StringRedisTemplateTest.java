package com.joezhou.springdata2redis.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author JoeZhou
 */
@SpringBootTest
class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void opsForValue() {
        // 存储a=1，60秒后过期
        stringRedisTemplate.opsForValue().set("a", "1", 60, TimeUnit.SECONDS);

        // 对a的值自增
        stringRedisTemplate.boundValueOps("a").increment(1);

        // 获取a
        System.out.println(stringRedisTemplate.opsForValue().get("a"));

        // 获取a的过期时间
        System.out.println(stringRedisTemplate.getExpire("a"));

        // 删除a
        stringRedisTemplate.delete("a");

        // 返回是否存在a
        System.out.println(stringRedisTemplate.hasKey("a"));
    }

    @Test
    void opsForSet() {

        // 向ages中存放set集合
        stringRedisTemplate.opsForSet().add("ages", "1", "2", "3");

        // 设置ages过期时间
        stringRedisTemplate.expire("ages", 5000, TimeUnit.MILLISECONDS);

        // 根据ages查看集合中是否存在指定数据
        System.out.println(stringRedisTemplate.opsForSet().isMember("ages", "1"));

        // 获取ageSet集合中的所有元素
        System.out.println(stringRedisTemplate.opsForSet().members("ages"));
    }
}
