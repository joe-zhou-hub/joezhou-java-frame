package com.joezhou.shieldspringbootstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoeZhou
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "joezhou.shield")
public class ShieldProperties {

    /**joezhou.shield.shieldWords="a,b,c"*/
    private String shieldWords;
}