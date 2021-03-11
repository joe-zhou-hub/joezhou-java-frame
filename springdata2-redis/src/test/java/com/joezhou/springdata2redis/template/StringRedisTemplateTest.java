package com.joezhou.springdata2redis.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author JoeZhou
 */
@SpringBootTest
class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void insertAccount() {
        // 向redis里存入username和设置缓存时间(5分钟)
        // 向redis里存入password
        stringRedisTemplate.opsForValue().set("username", "admin", 60 * 5, TimeUnit.SECONDS);
        // 对id进行自增
        stringRedisTemplate.opsForValue().set("password", "123");
        Boolean hasId = stringRedisTemplate.hasKey("id");
        if (hasId != null && hasId) {
            stringRedisTemplate.boundValueOps("id").increment(1);
            stringRedisTemplate.opsForValue().set("id", stringRedisTemplate.opsForValue().get("id"));
        } else {
            stringRedisTemplate.opsForValue().set("id", "0");
        }
    }

    @Test
    void selectAll() {

        // 获取username，username的过期时间，password
        String username = stringRedisTemplate.opsForValue().get("username");
        Long usernameExpire = stringRedisTemplate.getExpire("username");
        String password = stringRedisTemplate.opsForValue().get("password");

        // 获取password的过期时间并换算成指定单位
        Long passwordExpire = stringRedisTemplate.getExpire("password", TimeUnit.MINUTES);

        // 获取id
        String id = stringRedisTemplate.opsForValue().get("id");

        // 获取id的过期时间并换算成指定单位
        Long idExpire = stringRedisTemplate.getExpire("id", TimeUnit.MINUTES);

        Map<String, Object> map = new HashMap<>(3);
        map.put("编号", id);
        map.put("编号过期时间", idExpire);
        map.put("账号", username);
        map.put("账号过期时间", usernameExpire);
        map.put("密码", password);
        map.put("密码过期时间", passwordExpire);
        System.out.println(map);
    }

    @Test
    void deleteAccount() {

        Boolean hasId = stringRedisTemplate.hasKey("id");
        Boolean hasUsername = stringRedisTemplate.hasKey("username");
        Boolean hasPassword = stringRedisTemplate.hasKey("password");
        if (hasId != null && hasId) {
            stringRedisTemplate.delete("id");
        }
        if (hasUsername != null && hasUsername) {
            stringRedisTemplate.delete("username");
        }
        if (hasPassword != null && hasPassword) {
            stringRedisTemplate.delete("password");
        }
    }

    @Test
    void insertSet() {

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
