package com.joezhou.springboot2.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

/**
 * @author JoeZhou
 */
@Component
public class MyHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        long totalDiskSpace = 0L;
        long freeDiskSpace = 0L;

        // show all root disk name , [C:\, D:\, F:\]
        File[] rootFiles = File.listRoots();
        System.out.println(Arrays.toString(rootFiles));

        if (rootFiles != null && rootFiles.length > 0) {

            for (File file : rootFiles) {
                totalDiskSpace += file.getTotalSpace();
                freeDiskSpace += file.getUsableSpace();
            }

            return Health.up()
                    .withDetail("total", totalDiskSpace + "byte")
                    .withDetail("free", freeDiskSpace + "byte")
                    .build();
        }
        return Health.down().build();
    }
}