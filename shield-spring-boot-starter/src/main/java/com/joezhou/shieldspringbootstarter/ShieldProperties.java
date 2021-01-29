package com.joezhou.shieldspringbootstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JoeZhou
 */
@Data
@ConfigurationProperties("joezhou.shield")
public class ShieldProperties {

    /**
     * joezhou.shield.shieldWords="a,b,c"
     */
    private String shieldWords;

    /**
     * joezhou.shield.enable=true
     * enable shield if true
     */
    private Boolean enabled;
}