package com.joezhou.shieldspringbootstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoeZhou
 */
@Configuration
@ConditionalOnClass(ShieldOperations.class)
@EnableConfigurationProperties(ShieldProperties.class)
public class ShieldAutoConfiguration {

    private ShieldProperties shieldProperties;

    @Autowired
    public ShieldAutoConfiguration(ShieldProperties shieldProperties) {
        this.shieldProperties = shieldProperties;
    }

    /***
     * 必须写在 shieldOperations() 之上，否则因重复实例化相同 class 值而报错。
     * @return ShieldOperations实例
     */
    @Bean
    @ConditionalOnProperty(
            name = "joezhou.shield.enabled",
            havingValue = "true",
            matchIfMissing = true)
    public ShieldOperations shieldOperationsWithEnabled() {
        ShieldOperations shieldOperations = new ShieldOperations();
        shieldOperations.setShieldWords(shieldProperties.getShieldWords());
        return shieldOperations;
    }

    @Bean
    @ConditionalOnMissingBean
    public ShieldOperations shieldOperations() {
        ShieldOperations shieldOperations = new ShieldOperations();
        shieldOperations.setShieldWords(shieldProperties.getShieldWords());
        return shieldOperations;
    }

}